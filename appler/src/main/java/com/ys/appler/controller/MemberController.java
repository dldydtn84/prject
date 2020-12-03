package com.ys.appler.controller;


import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.MemberDto;
import com.ys.appler.dto.Message;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.MailService;
import com.ys.appler.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    BoardService boardService;


    @Autowired
   BCryptPasswordEncoder passwordEncoder;

    @Autowired
    MemberService memberService;

    @Autowired
    MailService mailService;


    @GetMapping("/user/login")
    public String login(HttpServletRequest request){


        return "/user/login";
    }


    @GetMapping("/user/singup")
    public String singup(){

        return "/user/singup";
    }

    @PostMapping("/user/singuppro")
    public String singuppro(@Valid MemberDto memberDto , Errors errors, Model model,HttpSession session){

           memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

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
           session.setAttribute("greeting", userid);
           memberService.memberSingupService(memberDto);

            return "redirect:/user/mypage?userid="+userid;
        }


    }

    @GetMapping("/user/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("userid") String userid){

        int result = memberService.memberidCheckService(userid);
        return result ;
    }








    @GetMapping("/user/mypage")
    public String mypage(@RequestParam("userid") String userid, Model model,MemberDto memberDto ){

        MemberDto contextread = memberService.memberReadService(userid);
        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);




        if(contextread.getProvider().equals("kakao")){
            model.addAttribute("id","kakao");
        }else if(contextread.getProvider().equals("naver")){
            model.addAttribute("id","naver");
        }else if(contextread.getProvider().equals("google")){
            model.addAttribute("id","google");
        }else if(contextread.getProvider().equals("form")){
            model.addAttribute("id",userid);
        }
        model.addAttribute("userid",userid);
        model.addAttribute("contextread",contextread);

        return "/user/mypage";
    }

    @PostMapping("/user/email")
    @ResponseBody
    public String sendEmail(HttpServletRequest request, String userEmail) throws Exception {
        String type ="auth";
         mailService.createMessage(userEmail, type);

        return "success";
    }
    @PostMapping("/user/emailcheck")
    @ResponseBody
    public int emailcheck(String authcode,String userid) { //인증코드를 받아서 처리
        log.info("Post verifyCode");

        int result = 0;

        if(MailService.ePw.equals(authcode)) {
            result =1;
           memberService.memberAuthService(userid);
        }

        return result;
    }
    @GetMapping("/user/idsearch")
    public String idsearch(){

        return "/user/idsearch";
    }
    @GetMapping("/user/pwsearch")
    public String pwsearch(){

        return "/user/idsearch";
    }

    @PostMapping("/user/id_search")
    @ResponseBody
    public String id_search(String id_name,String id_email, Model model){

        String userid=memberService.memberIdSearchService(id_name, id_email);

        if(userid == null){
            userid ="null";
        }
        model.addAttribute(userid,"userid");
        return userid ;
    }

   @GetMapping("/user/account_search")
    @ResponseBody
    public Object  account_search(String pw_id, String pw_email, Model model){
        //계정존재여부 확인
       int result=memberService.memberAccountSearchService(pw_id, pw_email); //count

       Map<String, Object> list = new HashMap<String, Object>();
       list.put("result", result);
       list.put("id", pw_id);
       list.put("email", pw_email);

       return list;
    }

    @PostMapping("/user/account_search")
    @ResponseBody
    public int account_success(String userid, String useremail, Model model) throws Exception {

        String type="account";
        mailService.createMessage(useremail, type);


        String temporaryPass= passwordEncoder.encode(MailService.pass);

       int result =  memberService.temporaryPasswordService(userid,temporaryPass);   //임시비밀번호 변경

        return result;
    }

    @PostMapping("/user/nowpassCheck")
    @ResponseBody
    public Boolean nowpassCheck(String userid, String nowpass, Model model) throws Exception {

        String password =  memberService.nowpassCheckService(userid);
        Boolean result = passwordEncoder.matches(nowpass,password);

        return result;
    }

    @PostMapping("/user/changePass")
    public ModelAndView changePass(@RequestParam("nowpass")String nowpass, @RequestParam("newpass")String newpass, @RequestParam("againpass") String againpass, ModelAndView mav, String userid, Model model) throws Exception {

        String password =  memberService.nowpassCheckService(userid);
        Boolean result = passwordEncoder.matches(nowpass,password);

        if(result == true){
            if(newpass.equals(againpass)) {
                int passresult = memberService.changePassService(userid, passwordEncoder.encode(newpass));

                mav.addObject("data", new Message("비밀번호가 변경되었습니다.", "/user/mypage?userid="+userid));
                mav.setViewName("Message");
                return mav;

            }
            mav.addObject("data", new Message("비밀번호가 일치하지 않습니다.", "/user/mypage?userid="+userid));
            mav.setViewName("Message");
            return mav;
        }

        mav.addObject("data", new Message("현재 비밀번호가 일치하지 않습니다.", "/user/mypage?userid="+userid));
        mav.setViewName("Message");

        return mav;


    }

    @PostMapping("/user/nicknameCheck")
    @ResponseBody
    public int nicknameCheck(String nickname, Model model) throws Exception {

        int result = memberService.nicknameCheckService(nickname);
        //닉네임 중복확인



    return result;
    }










    @PostMapping("/user/nicknamechange")
    public ModelAndView nicknamechange(@RequestParam("nickname") String nickname,@RequestParam("userid") String userid, Model model,ModelAndView mav) throws Exception {

        int result = memberService.nicknameCheckService(nickname);

        if(result == 0){

            int result2 = memberService.nicknameChangeService(nickname, userid);

            String changenickname = memberService.nickNameChangeSearchService(userid);

            mav.addObject("data", new Message("닉네임이 변경되었습니다.", "/user/mypage?userid="+userid));
            mav.setViewName("Message");

            return mav;

        }


        mav.addObject("data", new Message("닉네임을 사용할수 없습니다.", "/user/mypage?userid="+userid));
        mav.setViewName("Message");

        return mav;

    }











    @PostMapping("/user/namechange")

    public ModelAndView namechange(@RequestParam("name") String name,@RequestParam("userid") String userid,ModelAndView mav) throws Exception {

        int result = memberService.nameChangeService(name, userid);

        mav.addObject("data", new Message("이름이 변경되었습니다.", "/user/mypage?userid="+userid));
        mav.setViewName("Message");

        return mav;
      
    }

}
