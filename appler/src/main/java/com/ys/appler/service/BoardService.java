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

    public String Boardnum(int boardnum){
        String boardcode ="";
        if(boardnum == 1){
            boardcode ="FB";
        }else if(boardnum ==2){
            boardcode ="QB";
        }else if(boardnum ==3){
            boardcode ="CB";
        }else{
            System.out.println("error");
        }
        return boardcode;
    }


    public List<BoardDto> contextList(int board){
        String boardcode = Boardnum(board);
        List<BoardDto> boardlist = boardMapper.contextList(boardcode);
        return boardlist;
    }

    public BoardDto contextRead(int no,int board){
        String boardcode = Boardnum(board);

        BoardDto boardread = boardMapper.contextRead(boardcode,no);
        return  boardread;
    }
   public void contextWrite(BoardDto boardDto){


       boardMapper.contextWrite(boardDto);
    }

    public int postnoOne(String board_code) {
        int boardpostno = boardMapper.postnoOne(board_code);
        boardpostno += 1;
        return boardpostno;
    }
}
