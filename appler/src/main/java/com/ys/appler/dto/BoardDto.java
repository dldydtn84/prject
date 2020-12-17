package com.ys.appler.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Data
public class BoardDto {


    private int no;
    private int posts_no;
    private String board_code;
    @NotBlank(message = "제목을 입력해주세요")
    private String subject;
    private String nickname;
    private String editordata;
    private String files;
    private String ip;
    private String comment;
    private int readcount;
    private String reporting_date;

}



