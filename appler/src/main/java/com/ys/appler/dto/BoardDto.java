package com.ys.appler.dto;

import lombok.Getter;
import lombok.Setter;



@Getter @Setter
public class BoardDto {

    private int no;
    private int posts_no;
    private String board_code;
    private String subject;
    private String nickname;
    private String editordata;
    private String ip;
    private String files;
    private int readcount;
    private String reporting_date;
}



