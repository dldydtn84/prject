package com.ys.appler.controller;

import com.ys.appler.config.auth.PrincipalDetails;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.MemberDto;
import com.ys.appler.dto.PhotoBoardDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.MemberService;
import com.ys.appler.service.PhotoBoardService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails){
        System.out.println("testlogin ==============");
        PrincipalDetails principalDetails =(PrincipalDetails)authentication.getPrincipal();
        System.out.println("authentication :"+principalDetails.getMemberDto())  ;
        System.out.println("userDetails :"+userDetails.getMemberDto())  ;
        System.out.println("userDetails :"+userDetails.getPassword())  ;
        return "세션정보확인";
    }




    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(Authentication authentication){
        System.out.println("testlogin ==============");
        OAuth2User OAuth2User =(OAuth2User)authentication.getPrincipal();
        System.out.println("authentication :"+OAuth2User.getAttributes())  ;
        System.out.println("userDetails :"+OAuth2User.getName())  ;

        return "세션정보확인";
    }




    @GetMapping("/")
    public String index(Model model, HttpSession session  ) {

        List<BoardDto> fbcontextList = boardService.IndexContextListService("FB");
        List<BoardDto> qbcontextList = boardService.IndexContextListService("QB");
        List<BoardDto> cbcontextList = boardService.IndexContextListService("CB");

        List<PhotoBoardDto> photoList = photoBoardService.IndexPhotoListService();
        Object userid = session.getAttribute("greeting");


        model.addAttribute("fbcontextList",fbcontextList);
        model.addAttribute("qbcontextList",qbcontextList);
        model.addAttribute("cbcontextList",cbcontextList);
        model.addAttribute("photoList",photoList);
        model.addAttribute("userid",userid);

        String pass ="s8s0XQcD";
        String pass1 ="$2a$10$O2UPzPKGKYtA56l2wLiDAuZOxKVXbTxDaMuqjkSBzaJTJksQiFlx.";
        boolean pwmatch = passwordEncoder.matches(pass, pass1);


        System.out.println("pass : "+pwmatch);
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
