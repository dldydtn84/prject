package com.ys.appler.controller;

import com.ys.appler.dto.MemberDto;
import com.ys.appler.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;



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

    @GetMapping("/singuppro")
    public String singuppro(@Valid MemberDto memberDto , Errors errors, Model model){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String inputPass=memberDto.getPassword();
        memberDto.setPassword(passwordEncoder.encode(inputPass));



       if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("memberDto", memberDto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
                log.info("key : " +key);
                log.info("key2 : " +validatorResult.get(key));
            }

            return "/user/singup";
        }
        else {

           memberService.memberSingupService(memberDto);
            return "redirect:/";
        }

        /*return "redirect:/";*/
    }

    @GetMapping("/user/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("userid") String userid){

        int result = memberService.memberidCheckService(userid);
    log.info("result"+String.valueOf(result));
        return result ;
    }



}
