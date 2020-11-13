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

}
