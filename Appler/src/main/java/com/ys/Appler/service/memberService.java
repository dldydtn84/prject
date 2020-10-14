package com.ys.Appler.service;

import com.ys.Appler.dto.memberDto;
import com.ys.Appler.mapper.memberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class memberService {
    @Autowired
    private memberMapper memberMapper;

    public memberDto onemember() {
        return memberMapper.onemember();
    }

}
