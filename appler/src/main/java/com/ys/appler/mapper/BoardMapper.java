package com.ys.appler.mapper;

import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    List<BoardDto> contextList(String boardcode);
    List<BoardDto> BestcontextList();
    List<BoardDto> NewcontextList();

    int selectListno(String boardcode);
    BoardDto contextRead(String boardcode, int no);
    BoardDto contextReading(int no);

    void contextWrite(BoardDto boardDto);
    int postnoOne(String board_code);
    void readcountUp(int reviewNo);
    void contextDelete(Map<String, String> map);
    void contextUpdate(BoardDto boardDto);
    List<BoardDto> listPaging(Criteria criteria);
    List<BoardDto> IndexContextList(String boardcode);
}
