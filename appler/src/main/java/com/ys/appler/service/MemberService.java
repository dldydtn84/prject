package com.ys.appler.service;


import com.ys.appler.dto.MemberDto;
import com.ys.appler.mapper.MemberMapper;
import jdk.internal.jline.internal.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MemberService {

   @Autowired
    MemberMapper memberMapper;



    public void memberSingupService(MemberDto memberDto){




        memberMapper.memberSingup(memberDto);
    }
    public int oauthJoinService(MemberDto memberDto){
       int result = memberMapper.oauthJoin(memberDto);
        return result;
    }

    public MemberDto findByusernameService(String username){
       MemberDto result =  memberMapper.findByusername(username);
        return result;
    }
    public int memberidCheckService(String id){
         int result =  memberMapper.idCheck(id);
        return result;
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    public MemberDto memberReadService(String userid){
        MemberDto result =  memberMapper.memberRead(userid);
        return result;
    }
    public void memberAuthService(String userid){
          memberMapper.memberAuth(userid);

    }
    public String memberIdSearchService(String name,String email){
        String userid= memberMapper.memberIdSearch(name, email);
        return userid;
    }
    public int memberAccountSearchService(String id,String email){
        int result= memberMapper.memberAccountSearch(id, email);
        log.info("asdasda"+id+"asd : " + email);
        return result;
    }

    public int temporaryPasswordService(String id,String temporaryPass){
        int result= memberMapper.temporaryPassword(id, temporaryPass);

        return result;
    }
    public String nowpassCheckService(String id){
        String result= memberMapper.nowpassCheck(id);

        return result;
    }
    public int changePassService(String id,String changpass){
        int result= memberMapper.changePass(id, changpass);

        return result;
    }
    public int nicknameCheckService(String nickname){
        int result= memberMapper.nicknameCheck(nickname);

        return result;
    }
    public int nicknameChangeService(String nickname,String userid){
        int result= memberMapper.nicknameChange(nickname, userid);

        return result;
    }
    public int nameChangeService(String name,String userid){
        int result= memberMapper.nameChange(name, userid);

        return result;
    }

    public String nickNameChangeSearchService(String userid){
        String nicknameChange= memberMapper.nickNameChangeSearch(userid);

        return nicknameChange;
    }
    public String nameChangeSearchService(String userid){
        String nameChange= memberMapper.nameChangeSearch(userid);

        return nameChange;
    }
}
