package com.ys.appler;

import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.commons.paging.Pageing;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.CommentDto;
import com.ys.appler.dto.MemberDto;
import com.ys.appler.dto.NoticeBoardDto;
import com.ys.appler.mapper.BoardMapper;
import com.ys.appler.mapper.CommentMapper;
import com.ys.appler.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplerApplicationTests {

	private Logger log = LoggerFactory.getLogger(getClass());


	@Autowired
	BoardService boardService;


	@Test
	public void 게시글테스트() throws Exception{
		BoardDto boardDto =new BoardDto();
		for(int i=1; i<=100; i++){
			boardDto.setPosts_no(i);
			boardDto.setIp("127.0.0.1");
			boardDto.setFiles(null);
			boardDto.setEditordata("안녕하세요 인증게시판 테스트 게시글 "+i+"번 입니다.");
			boardDto.setSubject("테스트 "+i+"번 게시물");
			boardDto.setBoard_code("CB");
			boardDto.setNickname("tester");


			boardService.contextWriteService(boardDto);
		}

	}






}
