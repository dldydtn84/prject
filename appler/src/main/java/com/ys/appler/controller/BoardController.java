package com.ys.appler.controller;


import com.sun.tools.javac.util.StringUtils;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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


    @GetMapping("/write")
    public String write(@RequestParam("board") int boardnum, @ModelAttribute BoardDto boardDto, Model model, HttpServletRequest request) {
        String boardcode = "";
        if (boardnum == 1) {
            boardcode = "FB";
        } else if (boardnum == 2) {
            boardcode = "QB";
        } else if (boardnum == 3) {
            boardcode = "CB";
        } else {
            log.info("boardcode error");
        }

        model.addAttribute("boardnum", boardnum);
        model.addAttribute("boardcode", boardcode);


        return "/board/write";
    }

    @PostMapping("/writepro")
    public String writepro(@RequestParam("board_code") String board_code,
                           @RequestParam("boardnum") String boardnum,
                           @Valid BoardDto boardDto, BindingResult result,
                           RedirectAttributes redirect, HttpServletRequest request,
                           HttpServletResponse response, Model model) {
        int boardpostno = boardService.postnoOne(board_code);
        String IP = boardService.getIp(request);
        log.info("아이피는 " + IP);
        boardDto.setIp(IP);
        boardDto.setPosts_no(boardpostno);
        boardService.contextWrite(boardDto);


        return "redirect:/board/list?board=" + boardnum;
    }

    @GetMapping("/modify")
    public String modify(Model model) {

        return "/board/modify";
    }

    @PostMapping("/deletePro")
    public String deletePro(Model model,@RequestParam("board") int board ,@RequestParam("posts_no") int posts_no) {
        log.info(String.valueOf(board));
        boardService.contextDelete(board, posts_no);

        return "redirect:/board/list?board=" + board;
    }


    @RequestMapping(value = "/read")
    public String read(@RequestParam("board") int board, @RequestParam("posts_no") int posts_no, Model model,BoardDto boardDto, HttpServletRequest request, HttpServletResponse response) {

        BoardDto contextread = boardService.contextRead(posts_no, board);


        model.addAttribute("contextread", contextread);
        model.addAttribute("board", board);
        model.addAttribute("no", posts_no);

        //게시판 시퀀스번호
        int boardno=contextread.getNo();


        //쿠키가져오기
        Cookie[] cookies = request.getCookies();

        // 새로운쿠키 생성하여 비교
        Cookie viewCookie = null;

        // 쿠키가 있을 경우
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                // Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌
                if (cookies[i].getName().equals("cookie" + posts_no)) {
                    viewCookie = cookies[i];
                }
            }
        }
        // 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
        if (viewCookie == null) {

            // 쿠키 생성(이름, 값)
            Cookie newCookie = new Cookie("cookie" + posts_no, "|" + posts_no + "|");
            // 쿠키 추가
            response.addCookie(newCookie);


           boardService.readcountUp(boardno);
        }
        // viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
        else {
            // 쿠키 값 받아옴.
            String value = viewCookie.getValue();
        }


        return "/board/read";
    }


}
