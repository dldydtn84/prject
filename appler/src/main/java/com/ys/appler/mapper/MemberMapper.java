package com.ys.appler.mapper;

import com.ys.appler.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int idCheck(String id);
    MemberDto findByusername(String username);
    void memberSingup(MemberDto memberDto);
    int oauthJoin(MemberDto memberDto);
    MemberDto memberRead(String userid);
    void memberAuth(String userid);
    String memberIdSearch(String name,String email);
    int memberAccountSearch(String id, String email);
    int temporaryPassword(String id, String temporaryPass);
    int nowpassCheck(String id, String nowpass);
    int changePass(String id, String changpass);
    int nicknameCheck(String nickname);
    int nicknameChange(String nickname, String userid);
    int nameChange(String name, String userid);

}
