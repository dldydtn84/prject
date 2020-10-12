package com.ys.Appler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("index")
    public String index(Model model){
        //model.addAttribute("data","hello");
        return "index";
    }
    @GetMapping("/layout/default")
    public String defaults(Model model){
        //model.addAttribute("data","hello");
        return "/layout/default";
    }

}
