package com.ys.appler.service;

import com.ys.appler.dto.CommentDto;
import com.ys.appler.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    public int commentCount()throws Exception{
        int result = commentMapper.commentCount();
        return result;
    }

    public List<CommentDto> CommentListService(int p_no) throws Exception{
        List<CommentDto> result =commentMapper.commentList(p_no);
        return result;
    }
    public int commentInsertService(CommentDto commentDto) throws Exception {
        int result = commentMapper.commentInsert(commentDto);
        return result;
    }
    public int commentUpdateService(CommentDto commentDto) throws Exception{
        int result =  commentMapper.commentUpdate(commentDto);
        return result;
    }
    public int commentDeleteService(int no) throws Exception{
        int result = commentMapper.commentDelete(no);
        return result;
    }
}
