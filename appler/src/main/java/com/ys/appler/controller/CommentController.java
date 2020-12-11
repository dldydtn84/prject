package com.ys.appler.controller;

import com.ys.appler.dto.CommentDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    private String boardCode(int boardnum) {
        String boardcode = "";
        if (boardnum == 1) {
            boardcode = "FB";
        } else if (boardnum == 2) {
            boardcode = "QB";
        } else if (boardnum == 3) {
            boardcode = "CB";
        } else {

        }
        return boardcode;
    }

    @RequestMapping("/list") //댓글 리스트
    @ResponseBody
    private List<CommentDto> commentList(@RequestParam int bno,@RequestParam int board, Model model,CommentDto commentDto) throws Exception{
        String boardcode = boardCode(board);
        System.out.println("bno : "+bno + "board :"+boardcode);

        List<CommentDto> result = commentService.CommentListService(bno, boardcode);
        model.addAttribute("commentDto",commentDto);


        return result;
    }

    @RequestMapping("/insert") //댓글 작성
    @ResponseBody
    private int commentInsert(@RequestParam String p_no, @RequestParam int board , @RequestParam String comments, HttpSession session, HttpServletRequest request) throws Exception{

        BoardService boardService =new BoardService();
        String IP = boardService.getIp(request);
        String boardcode = boardService.Boardnum(board);


        CommentDto comment = new CommentDto();
        comment.setP_no(p_no);
        comment.setComments(comments);
        comment.setIp(IP);
        comment.setBoard_code(boardcode);
        comment.setNickname((String) session.getAttribute("greeting"));
         int result= commentService.commentInsertService(comment);

        return result;
    }

    @RequestMapping("/update") //댓글 수정  
    @ResponseBody
    private int commentUpdate(@RequestParam int no, @RequestParam String content) throws Exception{

        CommentDto comment = new CommentDto();
        comment.setNo(no);
        comment.setComments(content);

        return commentService.commentUpdateService(comment);
    }

    @RequestMapping("/delete/{no}") //댓글 삭제
    @ResponseBody
    private int commentDelete(@PathVariable int no) throws Exception{

        return commentService.commentDeleteService(no);
    }
}
