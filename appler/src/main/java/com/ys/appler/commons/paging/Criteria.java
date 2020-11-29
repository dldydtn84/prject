package com.ys.appler.commons.paging;

public class Criteria { //페이징 기준

    private int page;
    private int perPageNum;
    private String boardCode;
    private String search_option;
    private String keyword;

    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page <= 0) {  //page 음수처리
            this.page = 1;
            return;
        }


        this.page = page;
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        if (perPageNum <= 0 || perPageNum > 100) { //음수 또는 100이상시 개수를 10개로 고정
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;
    }

    public String getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }
    public int getPageStart(){ //시작번호
        return (this.page-1 ) * perPageNum;

    }

    public String getSearch_option() {
        return search_option;
    }

    public void setSearch_option(String search_option) {
        this.search_option = search_option;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
