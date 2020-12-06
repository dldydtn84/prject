package com.ys.appler.config.oauth;

import com.ys.appler.config.auth.PrincipalDetails;
import com.ys.appler.dto.MemberDto;
import com.ys.appler.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;


import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService  {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;


    private String provider;
    private String providerId;
    private String username;
    private String password;
    private String email;
    private String name;
    private String nickname;



    //구글로 부터 받은 데이터의 후처리 로직
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       log.info("userRequest : "+userRequest.getClientRegistration());



        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oauth2User = super.loadUser(userRequest);
        log.info("getAttributes : "+oauth2User.getAttributes());

        if(registrationId.equals("google")){

            provider = userRequest.getClientRegistration().getRegistrationId(); //google
            providerId= (String) oauth2User.getAttributes().get("sub");
            username = provider+"_"+providerId;
            password= passwordEncoder.encode("Appler");
            email= (String) oauth2User.getAttributes().get("email");
            name= (String) oauth2User.getAttributes().get("name");
            nickname = nNick();
        }else if(registrationId.equals("naver")){

           Map<String,Object> Attributes = (Map<String, Object>) oauth2User.getAttributes().get("response");



            provider = userRequest.getClientRegistration().getRegistrationId(); //naver
            providerId= (String) Attributes.get("id");
            username = provider+"_"+providerId;
            password= passwordEncoder.encode("Appler");
            email= (String) Attributes.get("email");
            name= (String) Attributes.get("name");
            nickname = nNick();

        }else if(registrationId.equals("kakao")) {



            Map<String,Object> Attributes = (Map<String, Object>) oauth2User.getAttributes().get("kakao_account");
            Map<String,Object> Attributes2 =(Map<String, Object>)  Attributes.get("profile");


            provider = userRequest.getClientRegistration().getRegistrationId(); //naver
            providerId= String.valueOf(oauth2User.getAttributes().get("id"));
             username = provider+"_"+providerId;
            password= passwordEncoder.encode("Appler");
            email= String.valueOf(Attributes.get("email"));
            name= String.valueOf(Attributes2.get("nickname"));
            nickname = nNick();

        }

        MemberDto member = memberService.findByusernameService(username);

        if(member == null){

          MemberDto memberDto= new MemberDto();
                memberDto.setUserid(username);
                memberDto.setPassword(password);
                 memberDto.setNickname(nickname);
                 memberDto.setName(name);
                memberDto.setEmail(email);
                memberDto.setProvider(provider);
                memberDto.setProviderid(providerId);


            int result =memberService.oauthJoinService(memberDto);
            return new PrincipalDetails(memberDto, oauth2User.getAttributes());

        } ;


        return new PrincipalDetails(member, oauth2User.getAttributes());
    }

    public static String nNick() {
        List<String> 닉 = Arrays.asList("기분나쁜","기분좋은","신바람나는","상쾌한","짜릿한","그리운","자유로운","서운한","울적한","비참한","위축되는","긴장되는","두려운","당당한","배부른","수줍은","창피한","멋있는", "열받은","심심한","잘생긴","이쁜","시끄러운");
        List<String> 네임 = Arrays.asList("사자","코끼리","호랑이","곰","여우","늑대","너구리","침팬치","고릴라","참새","고슴도치","강아지","고양이","거북이","토끼","앵무새","하이에나","돼지","하마","원숭이","물소","얼룩말","치타", "악어","기린","수달","염소","다람쥐","판다");
        Collections.shuffle(닉);
        Collections.shuffle(네임);
        String text = 닉.get(0)+네임.get(0);
        return text;
    }


}
