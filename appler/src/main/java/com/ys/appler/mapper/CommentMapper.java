package com.ys.appler.mapper;

import com.ys.appler.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {


  int commentCount() throws Exception;

  // 댓글 목록
  List<CommentDto> commentList(int p_no, String boardcode) throws Exception;

  // 댓글 작성
  int commentInsert(CommentDto commentDto) throws Exception;

  // 댓글 수정
  int commentUpdate(CommentDto commentDto) throws Exception;

  // 댓글 삭제
  int commentDelete(int no) throws Exception;

  //게시글 삭제시 댓글삭제
  int contextallcommentDelete(int p_no) throws Exception;


}
