package com.ys.appler.dto;

import lombok.Getter;
import lombok.Setter;



@Getter @Setter
public class BoardDto {

    private String posts_no;
    private String subject;
    private String contents;
    private String nickname;
    private String attachments;
    private int readcount;
    private String reporting_date;
}
