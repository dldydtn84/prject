package com.ys.appler.controller;

import com.ys.appler.dto.MemberDto;
import com.ys.appler.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public String index(Model model, HttpSession session){
       String id="";
        if(session.getAttribute("greeting") == null){
            id="null";
        }else{
            id=(String)session.getAttribute("greeting");
        }

        model.addAttribute("id",id);
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
