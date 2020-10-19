package com.ys.appler.controller;

import com.ys.appler.dto.FreeboardDto;
import com.ys.appler.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/board/freeboard")
    public String freeBoard(Model model){
        //model.addAttribute("data","hello");
        List<FreeboardDto> contextlist = boardService.contextList();
        model.addAttribute("contextlist", contextlist);
        return "/board/freeBoard";
    }
    @GetMapping("/board/questionboard")
    public String question(Model model){
        //model.addAttribute("data","hello");
        return "/board/questionBoard";
    }
    @GetMapping("/board/certifficationboard")
    public String certiffication(Model model){
        //model.addAttribute("data","hello");
        return "/board/certifficationBoard";
    }

    @GetMapping("/board/write")
    public String write(Model model){
        //model.addAttribute("data","hello");
        return "/board/write";
    }
}
