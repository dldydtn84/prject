package com.ys.appler.dto;

import lombok.Getter;
import lombok.Setter;




public class BoardDto extends ListPagingDto{

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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPosts_no() {
        return posts_no;
    }

    public void setPosts_no(int posts_no) {
        this.posts_no = posts_no;
    }

    public String getBoard_code() {
        return board_code;
    }

    public void setBoard_code(String board_code) {
        this.board_code = board_code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEditordata() {
        return editordata;
    }

    public void setEditordata(String editordata) {
        this.editordata = editordata;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public String getReporting_date() {
        return reporting_date;
    }

    public void setReporting_date(String reporting_date) {
        this.reporting_date = reporting_date;
    }
}



