package com.ys.appler.controller;

import com.ys.appler.dto.MemberDto;
import com.ys.appler.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    MemberService testService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model, HttpSession session){
        String pass ="test";
        String pass1 ="$2a$10$OprFunQpXXQdrofVkDkPAe.bTxQJX29tNlm3gwlcpg2VzRnI8BDay";
        boolean pwmatch = passwordEncoder.matches(pass, pass1);
        System.out.println(pwmatch);
        return "index";
    }

    @GetMapping("/admin/layout/default")
    public String defaults(Model model){
        //model.addAttribute("data","hello");
        return "/admin/layout/default";
    }
    @GetMapping("default")
    public String defaultss(Model model){
        //model.addAttribute("data","hello");
        return "default";
    }

    @GetMapping("/login")
    public String login(){

        return "/user/login";
    }
    @PostMapping("/login")
    public String loginp(){

        return "/user/login";
    }
}
