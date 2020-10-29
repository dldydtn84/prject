package com.ys.appler.mapper;

import com.ys.appler.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface BoardMapper {


    List<BoardDto> contextList(String boardcode);
    BoardDto contextRead(String boardcode, int no);
    void contextWrite(BoardDto boardDto);
    int postnoOne(String board_code);
    int readcountup(int reviewNo);
}
