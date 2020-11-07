package com.ys.appler.service;

import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.NoticeBoardDto;
import com.ys.appler.mapper.NoticeBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeBoardService {

    @Autowired
    NoticeBoardMapper noticeBoardMapper;


    public void contextWriteService( ){


        /*noticeBoardMapper.contextWrite();*/
    }

    public void contextWriteService(NoticeBoardDto noticeBoardDto){


        noticeBoardMapper.contextWrite(noticeBoardDto);
    }





    /*public List<BoardDto> contextListService(int board){


        return boardlist;
    }*/

   /* public int selectListnoService(int board){

        String boardcode = Boardnum(board);
        int listno = boardMapper.selectListno(boardcode);




        return listno;
    }*/
/*
    public BoardDto contextReadService(int no,int board){
        String boardcode = Boardnum(board);

        BoardDto boardread = boardMapper.contextRead(boardcode,no);
        return  boardread;
    }
    public void contextWriteService(BoardDto boardDto){


        boardMapper.contextWrite(boardDto);
    }

    public int postnoOneService(String board_code) {
        int boardpostno = boardMapper.postnoOne(board_code);
        boardpostno += 1;
        return boardpostno;
    }

    public void readcountUpService(int reviewNo){
        boardMapper.readcountUp(reviewNo);

    }
    public void contextDeleteService(int board , int posts_no){
        String boardcode = Boardnum(board);
        Map<String, String> map = new HashMap<String, String>();
        map.put("boardcode", boardcode);
        map.put("posts_no", String.valueOf(posts_no));


        boardMapper.contextDelete(map);
    }
    public void contextUpdateService(BoardDto boardDto){
        boardMapper.contextUpdate(boardDto);

    }
    public List<BoardDto> listPagingService(Criteria criteria){



        List<BoardDto> result = boardMapper.listPaging(criteria);

        return result;
    }*/
}
