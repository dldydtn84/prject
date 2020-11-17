package com.ys.appler.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class QuestBoardDto {
    private int no;
    private String board_code;
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    private String pnum1;
    private String pnum2;
    private String pnum3;
    private String phone;

    @NotBlank(message = "제목은 필수 입력사항입니다.")
    private String subject;
    @NotBlank(message = "내용은 필수 입력사항입니다.")
    private String contents;
    private String file;
    private String ip;
    private int readcount;
    private Date reporting_date;

}