package com.ys.appler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login(){

        return "/user/login";
    }
    @PostMapping("/login")
    public String loginp(){

        return "redirect:/";
    }
    @GetMapping("/singup")
    public String singup(){

        return "/user/singup";
    }


}
