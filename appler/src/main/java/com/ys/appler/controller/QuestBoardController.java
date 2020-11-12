package com.ys.appler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/questboard")
@Controller
public class QuestBoardController {



    @GetMapping("/write")
    public String write() {

        return "/questboard/write";
    }
    @PostMapping("/write")
    public String writepro() {

        return "/questboard/writesussce";
    }
}
