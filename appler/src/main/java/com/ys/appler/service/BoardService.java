package com.ys.appler.service;

import com.ys.appler.dto.FreeboardDto;
import com.ys.appler.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public List<FreeboardDto> contextList(String name){
        List<FreeboardDto> boardlist = boardMapper.contextList(name);
        return boardlist;
    }
}
