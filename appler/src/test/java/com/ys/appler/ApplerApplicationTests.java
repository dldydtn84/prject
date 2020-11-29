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
	BoardMapper boardMapper;
	@Autowired
	CommentMapper commentMapper;
	@Autowired
	CommentService commentService;
	@Autowired
	BoardService boardService;



	@Autowired
	MemberService memberService;

	@Autowired
	PhotoBoardService photoBoardService;




	@Autowired
	NoticeBoardService noticeBoardService;


	@Test
	public void 게시글테스트() throws Exception{
		NoticeBoardDto noticeBoardDto =new NoticeBoardDto();
		for(int i=1; i<10; i++){

		noticeBoardDto.setIp("127.0.0.1");
		noticeBoardDto.setFile(null);
		noticeBoardDto.setContents("aasdsadasd");
		noticeBoardDto.setSubject("asd"+i+"입니다.");
		noticeBoardDto.setBoard_code("NB");
		noticeBoardDto.setNickname("test");

			noticeBoardService.contextWriteService(noticeBoardDto);
		}

	}






}
