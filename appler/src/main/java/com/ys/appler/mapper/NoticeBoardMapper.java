package com.ys.appler.mapper;


import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.NoticeBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeBoardMapper {

    void contextWrite(NoticeBoardDto noticeBoardDto);
    int ListCount();
    List<NoticeBoardDto> listPaging(Criteria criteria);
    NoticeBoardDto contextRead(int no);
    void readcountUp(int reviewNo);
    void contextUpdate(NoticeBoardDto noticeBoardDto);
    void contextDelete(Map<String, String> map);
    List<NoticeBoardDto> contextSearch(String Search);
}
