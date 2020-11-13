package com.ys.appler.controller;

import com.ys.appler.dto.MemberDto;
import com.ys.appler.dto.NoticeBoardDto;
import com.ys.appler.service.MailService;
import com.ys.appler.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    MailService mailService;


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

    @PostMapping("/singuppro")
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
        String userid =memberDto.getUserid();
           memberService.memberSingupService(memberDto);
            return "/user/mypage?userid"+userid;
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








    @GetMapping("/user/mypage")
    public String mypage(@RequestParam("userid") String userid, Model model ){

        MemberDto contextread = memberService.memberReadService(userid);


        model.addAttribute("userid",userid);
        model.addAttribute("contextread",contextread);

        return "/user/mypage";
    }

    @PostMapping("/user/email")
    @ResponseBody
    public String sendEmail(HttpServletRequest request, String userEmail) throws Exception {
        log.info("useremail"+ userEmail);
         mailService.createMessage(userEmail);

        return "success";
    }
    @PostMapping("/user/emailcheck")
    @ResponseBody
    public int emailcheck(String authcode,String userid) { //인증코드를 받아서 처리
        log.info("Post verifyCode");

        int result = 0;
        System.out.println("code : "+authcode);
        System.out.println("code match : "+ MailService.ePw.equals(authcode)); //이멜로 보낸 번호와 비교
        if(MailService.ePw.equals(authcode)) {
            result =1;
           memberService.memberAuthService(userid);
        }

        return result;
    }


}
