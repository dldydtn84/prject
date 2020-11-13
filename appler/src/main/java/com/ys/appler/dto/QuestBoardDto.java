package com.ys.appler.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class QuestBoardDto {
    private int no;
    private String board_code;
    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    private String email;
    private String pnum1;
    private String pnum2;
    private String pnum3;
    private String phone;
    private String subject;
    private String contents;
    private String file;
    private String ip;
    private int readcount;
    private Date reporting_date;

}