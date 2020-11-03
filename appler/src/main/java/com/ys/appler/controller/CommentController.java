package com.ys.appler.controller;

import com.ys.appler.dto.CommentDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("/list") //댓글 리스트
    @ResponseBody
    private List<CommentDto> commentList(Model model) throws Exception{
        List<CommentDto> result = commentService.CommentListService();
        return result;
    }

    @RequestMapping("/insert") //댓글 작성
    @ResponseBody
    private int commentInsert(@RequestParam String p_no, @RequestParam String board , @RequestParam String contents, HttpSession session, HttpServletRequest request) throws Exception{
        BoardService boardService =new BoardService();
        String IP = boardService.getIp(request);
        String boardcode = boardService.Boardnum(Integer.parseInt(board));


        CommentDto comment = new CommentDto();
        comment.setP_no(p_no);
        comment.setContents(contents);
        comment.setIp(IP);
        comment.setBoard_code(boardcode);
        comment.setNickname((String) session.getAttribute("greeting"));

        return commentService.commentInsertService(comment);
    }

    @RequestMapping("/update") //댓글 수정  
    @ResponseBody
    private int commentUpdate(@RequestParam int no, @RequestParam String contents) throws Exception{

        CommentDto comment = new CommentDto();
        comment.setNo(no);
        comment.setContents(contents);

        return commentService.commentUpdateService(comment);
    }

    @RequestMapping("/delete/{cno}") //댓글 삭제  
    @ResponseBody
    private int commentDelete(@PathVariable int no) throws Exception{

        return commentService.commentDeleteService(no);
    }
}
