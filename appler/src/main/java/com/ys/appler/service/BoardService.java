package com.ys.appler.service;

import com.ys.appler.dto.BoardDto;
import com.ys.appler.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public String boardnum(int boardnum){
        String board ="";
        if(boardnum == 1){
            board ="tbl_free_board";
        }else if(boardnum ==2){
            board ="tbl_question_board";
        }else if(boardnum ==3){
            board="tbl_certification_board";
        }else{
            System.out.println("error");
        }
        return board;
    }


    public List<BoardDto> contextList(int boardnum){
        String board = boardnum(boardnum);
        List<BoardDto> boardlist = boardMapper.contextList(board);
        return boardlist;
    }

    public BoardDto contextRead(int no,int boardnum){
        String board = boardnum(boardnum);
        BoardDto boardread = boardMapper.contextRead(board,no);
        return  boardread;
    }
    public void contextwrite(int boardnum){
        String board = boardnum(boardnum);
       boardMapper.contextWrite(board);

    }
}
