package com.ys.appler.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
public class BoardDto{


    private int no;
    private int posts_no;
    private String board_code;
    private String subject;
    private String nickname;
    private String editordata;
    private String files;
    private String ip;
    private String comment;
    private int readcount;
    private String reporting_date;


}



