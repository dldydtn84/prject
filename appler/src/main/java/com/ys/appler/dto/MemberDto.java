package com.ys.appler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {

    private String id;
    private String pass;
    private String nickname;
    private String name;
    private String email;
    private char level;
    private int point;
    private String datetime;
}
