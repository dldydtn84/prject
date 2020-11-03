package com.ys.appler.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CommentDto {


    private int no; //seq
    private String p_no; //게시판 post_no
    private String board_code; //게시판 board
    private String comments;
    private String nickname;
    private Date r_date;
    private String ip;

}
