package com.ys.appler.mapper;


import com.ys.appler.dto.NoticeBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeBoardMapper {

    void contextWrite(NoticeBoardDto noticeBoardDto);


/*    List<BoardDto> contextList(String boardcode);
    int selectListno(String boardcode);
    BoardDto contextRead(String boardcode, int no);
    void contextWrite(BoardDto boardDto);
    int postnoOne(String board_code);
    void readcountUp(int reviewNo);
    void contextDelete(Map<String, String> map);
    void contextUpdate(BoardDto boardDto);
    List<BoardDto> listPaging(Criteria criteria);*/
}
