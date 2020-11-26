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
    public @ResponseBody String testLogin( @AuthenticationPrincipal PrincipalDetails PrincipalDetails){
        System.out.println("testlogin ==============");


        System.out.println("userDetails :"+PrincipalDetails.getMemberDto())  ;
        System.out.println("userDetails :"+PrincipalDetails.getPassword())  ;
        return "세션정보확인";
    }




    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(Model model,@AuthenticationPrincipal PrincipalDetails PrincipalDetails){
        System.out.println("testlogin ==============");

        System.out.println("PrincipalDetails :"+PrincipalDetails.getMemberDto())  ;


        return "세션정보확인";
    }




    @GetMapping("/")
    public String index(Model model, HttpSession session ,@AuthenticationPrincipal PrincipalDetails principalDetails ) {
        System.out.println("한글테스트 ==============");


/*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails user = (PrincipalDetails) authentication.getPrincipal();

        System.out.println("tt ::"+user.getUsername());*/
/*

        System.out.println("tasdasdasdat ::"+  principalDetails.getAttributes().get);
*/


        List<BoardDto> fbcontextList = boardService.IndexContextListService("FB");
        List<BoardDto> qbcontextList = boardService.IndexContextListService("QB");
        List<BoardDto> cbcontextList = boardService.IndexContextListService("CB");

        List<PhotoBoardDto> photoList = photoBoardService.IndexPhotoListService();
        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();



        model.addAttribute("fbcontextList",fbcontextList);
        model.addAttribute("qbcontextList",qbcontextList);
        model.addAttribute("cbcontextList",cbcontextList);
        model.addAttribute("photoList",photoList);

        model.addAttribute("bestcontextList",bestcontextList);
        model.addAttribute("newcontextList",newcontextList);



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
