package com.ys.appler.mapper;

import com.ys.appler.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommentMapper {


     int commentCount() throws Exception;
    // 댓글 목록
     List<CommentDto> commentList(int p_no) throws Exception;
    // 댓글 작성
    int commentInsert(CommentDto commentDto) throws Exception;
    // 댓글 수정
    int commentUpdate(CommentDto commentDto) throws Exception;
    // 댓글 삭제
    int commentDelete(int no) throws Exception;


}
