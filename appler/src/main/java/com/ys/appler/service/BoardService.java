package com.ys.appler.service;

import com.ys.appler.dto.BoardDto;
import com.ys.appler.mapper.BoardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



@Service
public class BoardService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public String getIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        logger.info(">>>> X-FORWARDED-FOR : " + ip);

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.info(">>>> Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
            logger.info(">>>> WL-Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>> HTTP_CLIENT_IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        logger.info(">>>> Result : IP Address : "+ip);

        return ip;

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

    public int readcountup(int reviewNo){
        int readcount = boardMapper.readcountup(reviewNo);
        return readcount;
    }
}
