package com.ys.appler.mapper;

import com.ys.appler.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int idCheck(String id);
    void memberSingup(MemberDto memberDto);
    MemberDto memberRead(String userid);
    void memberAuth(String userid);
}
