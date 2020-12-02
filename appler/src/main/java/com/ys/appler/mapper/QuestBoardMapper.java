package com.ys.appler.mapper;

import com.ys.appler.dto.QuestBoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestBoardMapper {
    void contextWrite(QuestBoardDto questBoardDto);

}
