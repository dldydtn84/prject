package com.ys.Appler.service;

import com.ys.Appler.dto.memberDto;
import com.ys.Appler.mapper.memberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class memberServiceImpl implements memberService {
    @Autowired
    private memberMapper memberMapper;


    @Override
    public memberDto onemember() {
        return memberMapper.selectmember();
    }
}
