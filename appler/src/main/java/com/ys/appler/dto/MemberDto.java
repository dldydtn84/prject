package com.ys.appler.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter @Setter
public class MemberDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String userid;
    private String password;
    private String nickname;
    private String name;
    private String email;
    private String enabled;
    private String authority;
    private int point;
    private Date lastdate;
    private Date date;
}
