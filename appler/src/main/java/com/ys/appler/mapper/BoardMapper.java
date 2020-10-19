package com.ys.appler.mapper;

import com.ys.appler.dto.FreeboardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<FreeboardDto> contextList();
}
