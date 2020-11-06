package com.ys.appler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class CsboardController {
    @GetMapping("/csboard/notice/list")
    public String nlist() {

        return "/csboard/notice/list";
    }
    @GetMapping("/csboard/quest/list")
    public String qlist() {

        return "/csboard/quest/list";
    }
    @GetMapping("/csboard/ask/list")
    public String alist() {

        return "/csboard/ask/list";
    }
}
