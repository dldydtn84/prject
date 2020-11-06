package com.ys.appler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/itboard")
public class ItboardController {

    @GetMapping("/list")
    public String list() {

        return "/itboard/list";
    }
    @GetMapping("/write")
    public String write() {

        return "/itboard/write";
    }
}
