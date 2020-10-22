package com.ys.appler.controller;

import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.MemberDto;
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
    public String list(@RequestParam("board") int boardnum, Model model) {

        List<BoardDto> contextlist = boardService.contextList(boardnum);
        model.addAttribute("contextlist", contextlist);
        model.addAttribute("boardnum", boardnum);
        return "/board/list";
    }

    @GetMapping("/read")
    public String read(@RequestParam("board") int boardnum, @RequestParam("no") int no, Model model) {
        BoardDto contextread = boardService.contextRead(no, boardnum);
        model.addAttribute("contextread", contextread);
        model.addAttribute("board", boardnum);
        model.addAttribute("no", no);
        return "/board/read";
    }


    @GetMapping("/write")
    public String write(@RequestParam("board") int boardnum, Model model) {
        model.addAttribute("boardnum", boardnum);


        return "/board/write";
    }

    @GetMapping("/writepro")
    public String writepro(@RequestParam("board") int boardnum , @ModelAttribute BoardDto boardDto, Model model) {


        boardService.contextwrite(boardnum);
        return "/board/writepro";
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
