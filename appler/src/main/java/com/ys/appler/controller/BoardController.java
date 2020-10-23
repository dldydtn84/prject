package com.ys.appler.controller;

import com.ys.appler.dto.BoardDto;
import com.ys.appler.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;



    @GetMapping("/list")
    public String list(@RequestParam("board") int board, Model model) {

        List<BoardDto> contextlist = boardService.contextList(board);
        model.addAttribute("contextlist", contextlist);
        model.addAttribute("board", board);
        return "/board/list";
    }

    @GetMapping("/read")
    public String read(@RequestParam("board") int board, @RequestParam("no") int no, Model model) {
        BoardDto contextread = boardService.contextRead(no, board);
        model.addAttribute("contextread", contextread);
        model.addAttribute("board", board);
        model.addAttribute("no", no);
        return "/board/read";
    }


    @GetMapping("/write")
    public String write(@RequestParam("board") int boardnum, Model model) {
        String boardcode ="";
        if(boardnum == 1){
            boardcode ="FB";
        }else if(boardnum ==2){
            boardcode ="QB";
        }else if(boardnum ==3){
            boardcode ="CB";
        }else{
            System.out.println("error");
        }

        model.addAttribute("boardcode", boardcode);


        return "/board/write";
    }

    @GetMapping("/writepro")
    public String writepro(@RequestParam("board_code") String board_code,@ModelAttribute BoardDto boardDto, Model model) {
        int boardpostno = boardService.postnoOne(board_code);
        boardDto.setPosts_no(boardpostno);
        boardService.contextWrite(boardDto);

        return "/";
    }
    @GetMapping("/modify")
    public String modify(Model model) {

        return "/board/modify";
    }
    @GetMapping("/deletePro")
    public String deletePro(Model model) {

        return "/board/deletePro";
    }

}
