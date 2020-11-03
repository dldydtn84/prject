package com.ys.appler.service;

import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.commons.paging.Pageing;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
               log.info("boardcode error");
        }
        return boardcode;
    }

    public String getIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

         log.info(">>>> X-FORWARDED-FOR : " + ip);

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
             log.info(">>>> Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
             log.info(">>>> WL-Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
             log.info(">>>> HTTP_CLIENT_IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                   log.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

          log.info(">>>> Result : IP Address : "+ip);

        return ip;

    }




    public List<BoardDto> contextListService(int board){
        String boardcode = Boardnum(board);
        List<BoardDto> boardlist = boardMapper.contextList(boardcode);
        return boardlist;
    }

    public int selectListnoService(int board){

        String boardcode = Boardnum(board);
        int listno = boardMapper.selectListno(boardcode);




        return listno;
    }

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
    }


}
