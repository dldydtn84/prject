package com.ys.appler.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class MemberDto {

    private int no;
    @NotBlank(message = "아이디를 입력해주세요")
    private String userid;
    @Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "비밀번호는 영어와 숫자로 포함해서 6~12자리 이내로 입력해주세요.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String email;
    private String enabled;
    private String authority;
    private String emailauth;
    private String provider;
    private String providerid;
    private int point;
    private Date lastdate;
    private Date date;
}
