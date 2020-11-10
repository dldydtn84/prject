package com.ys.appler.commons.paging;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pageing {

    private int totalCount;
    private  int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 10;

    private  int halfstartPage;
    private int halfendPage;
    private boolean halfprev;
    private boolean halfnext;
    private int halfdisplayPageNum = 5;
    private Criteria criteria;

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public void setTotalCount(int totalCount){
        this.totalCount=totalCount;
        calcData();
        halfcalcData();
    }
    private void calcData(){
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) +1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

        if(endPage > tempEndPage){
            endPage =tempEndPage;
        }

        prev = startPage ==1 ? false: true;
        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;

    }
    private void halfcalcData(){
        halfendPage = (int) (Math.ceil(criteria.getPage() / (double) halfdisplayPageNum) * halfdisplayPageNum);
        halfstartPage = (halfendPage - halfdisplayPageNum) +1;

        int halftempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

        if(halfendPage > halftempEndPage){
            halfendPage =halftempEndPage;
        }

        halfprev = halfstartPage ==1 ? false: true;
        halfnext = halfendPage * criteria.getPerPageNum() >= totalCount ? false : true;

    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public int getHalfstartPage() {
        return halfstartPage;
    }

    public int getHalfendPage() {
        return halfendPage;
    }

    public boolean isHalfprev() {
        return halfprev;
    }

    public boolean isHalfnext() {
        return halfnext;
    }
}
