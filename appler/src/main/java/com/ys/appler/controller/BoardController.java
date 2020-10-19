package com.ys.appler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @GetMapping("/board/freeboard")
    public String freeBoard(Model model){
        //model.addAttribute("data","hello");
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
