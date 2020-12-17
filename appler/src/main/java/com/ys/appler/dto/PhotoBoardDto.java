package com.ys.appler.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PhotoBoardDto {

  private int no;
  private String board_code;
  private String nickname;
  private String subject;
  private String contents;
  private String file;
  private String ip;
  private int readcount;
  private Date reporting_date;

}
