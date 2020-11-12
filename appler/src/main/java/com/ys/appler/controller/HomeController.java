package com.ys.appler.controller;

import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.MemberDto;
import com.ys.appler.dto.PhotoBoardDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.MemberService;
import com.ys.appler.service.PhotoBoardService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

   /* @Autowired
    MemberService memberService;*/

    @Autowired
    BoardService boardService;

    @Autowired
    PhotoBoardService photoBoardService;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model, HttpSession session  ) {

        List<BoardDto> fbcontextList = boardService.IndexContextListService("FB");
        List<BoardDto> qbcontextList = boardService.IndexContextListService("QB");
        List<BoardDto> cbcontextList = boardService.IndexContextListService("CB");

        List<PhotoBoardDto> photoList = photoBoardService.IndexPhotoListService();

        model.addAttribute("fbcontextList",fbcontextList);
        model.addAttribute("qbcontextList",qbcontextList);
        model.addAttribute("cbcontextList",cbcontextList);
        model.addAttribute("photoList",photoList);


        String pass ="1";
        String pass1 ="$2a$10$aa1NePEG25qs0oxCRyS1GOq5WF12fFrdel2ZxrTcqeeum5fu25Ljy";
        boolean pwmatch = passwordEncoder.matches(pass, pass1);


        System.out.println(pwmatch);
        return "index";
    }

    @GetMapping("/chat")
    public String chat(Model model){

       /* User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();*/
/*
        log.info("{} username " +user.getUsername());
        log.info("{] getname " + user.getName());

        model.addAttribute("userid",user.getUsername());*/


        return "chat";
    }
    @GetMapping("/test")
    public String test(Model model){

        return "test";
    }
    @GetMapping("/test2")
    public String test2(Model model){

        return "test2";
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


}
