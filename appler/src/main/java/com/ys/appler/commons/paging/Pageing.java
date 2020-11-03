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
    private Criteria criteria;

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public void setTotalCount(int totalCount){
        this.totalCount=totalCount;
        calcData();
    }
    private void calcData(){
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) +1;
        log.info(String.valueOf(endPage));
        log.info(String.valueOf(startPage));

        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

        if(endPage > tempEndPage){
            endPage =tempEndPage;
        }
        prev = startPage ==1 ? false: true;
        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;

        log.info(String.valueOf(prev));
        log.info(String.valueOf(next));
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
}
