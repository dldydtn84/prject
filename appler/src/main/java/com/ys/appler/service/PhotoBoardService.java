package com.ys.appler.service;

import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.PhotoBoardDto;
import com.ys.appler.mapper.PhotoBoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class PhotoBoardService {

    @Autowired
    PhotoBoardMapper photoBoardMapper;

    private final String UPLOAD_PATH = "c:" + File.separator + "temp" + File.separator;

    public String saveFile(MultipartFile file) throws IOException {

        // 파일 이름 변경
        UUID uuid = UUID.randomUUID();
        String saveName = uuid + "_" + file.getOriginalFilename();

        log.info("saveName: {}",saveName);

        String fileName = file.getOriginalFilename();
        log.info("fileName : "+fileName);
        String contentType = file.getContentType();
        long filesize = file.getSize();

        // 저장할 File 객체를 생성(껍데기 파일)
        File saveFile = new File(UPLOAD_PATH, saveName); // 저장할 폴더 이름, 저장할 파일 이름
        log.info("저장파일 : "+String.valueOf(saveFile));
        try {
            file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return saveName;
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

    public List<PhotoBoardDto> contextListService(){

        List<PhotoBoardDto> boardlist = photoBoardMapper.contextList();
        return boardlist;
    }

    public void contextWriteService(PhotoBoardDto photoBoardDto){


        photoBoardMapper.contextWrite(photoBoardDto);
    }


    /*
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
 */

}
