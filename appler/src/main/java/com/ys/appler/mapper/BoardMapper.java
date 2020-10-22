package com.ys.appler.mapper;

import com.ys.appler.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDto> contextList(String board);
    BoardDto contextRead(String board, int no);
    void contextWrite(String board);
}
