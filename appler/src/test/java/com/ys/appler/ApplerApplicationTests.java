package com.ys.appler;

import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.commons.paging.Pageing;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.CommentDto;
import com.ys.appler.dto.NoticeBoardDto;
import com.ys.appler.mapper.BoardMapper;
import com.ys.appler.mapper.CommentMapper;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.CommentService;
import com.ys.appler.service.NoticeBoardService;
import com.ys.appler.service.PhotoBoardService;
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
	PhotoBoardService photoBoardService;




	@Autowired
	NoticeBoardService noticeBoardService;


	@Test
	public void 테스트(){
		NoticeBoardDto noticeBoardDto=new NoticeBoardDto();
		noticeBoardDto.setNickname("nick");
		noticeBoardDto.setSubject("제목");
		noticeBoardDto.setContents("내용입니다.");
		noticeBoardDto.setFile(null);
		noticeBoardDto.setIp("127.0.0.1");

		noticeBoardService.contextWriteService(noticeBoardDto);
	}

	/*@Test
	public void paging(){

		Criteria criteria = new Criteria();
		criteria.setPage(5);
		criteria.setPerPageNum(20);
*//*		criteria.setBoardCode("FB");*//*
		*//*List<BoardDto> test = boardMapper.listPaging(criteria);*//*


		*//*for(BoardDto tests: test){
			log.info(tests.getPosts_no()+":"+tests.getSubject());
		}*//*

	}

*/
/*
	@Test
	public void pagingmak(){

		Criteria criteria =new Criteria();
		criteria.setPage(1);
		criteria.setPerPageNum(15);

log.info("getpage: "+ criteria.getPage()+"getBper" + criteria.getPerPageNum());


		Pageing pageing = new Pageing();
		pageing.setCriteria(criteria);
		pageing.setTotalCount(1000);




	}*/

	@Test
	public void testCreate() throws Exception{
		for(int i =1; i<=1000; i++){
			BoardDto boardDto = new BoardDto();
			boardDto.setPosts_no(i);
			boardDto.setSubject("자유게시판"+i+"번째 글 제목입니다.");
			boardDto.setEditordata(i+"번째 글내용입니다.");
			boardDto.setBoard_code("FB");
			boardDto.setNickname("nick");
			boardDto.setComment("0");
			boardDto.setReadcount(0);
			boardDto.setIp("127.0.0.1");
			boardMapper.contextWrite(boardDto);
		}

	}
/*

	@Test
	public void commenttest() throws Exception{
		for(int i =1; i<=10; i++){
			CommentDto commentDto = new CommentDto();
			commentDto.setP_no("1000");
			commentDto.setBoard_code("FB");
			commentDto.setComments(i+"번째 댓글입니다.");
			commentDto.setIp("127.0.0.1");
			commentDto.setNickname("nick");

			commentMapper.commentInsert(commentDto);
		}

	}

	@Test
	public void commentselect() throws Exception{
		int count = commentMapper.commentCount();
log.info(String.valueOf(count));
	}
	@Test
	public void commentdelete() throws Exception{
		int no = 10;
		 commentMapper.commentDelete(no);

	}
	@Test
	public void commentinsert() throws Exception{
		CommentDto comment = new CommentDto();
		comment.setP_no("1000");
		comment.setComments("asdsaadsadasddas");
comment.setBoard_code("FB");
		comment.setNickname("nick");
		comment.setIp("127.0.0.1");
		 commentService.commentInsertService(comment);

	}
*/


	@Test
	public void 게시글삭제시댓글삭제() throws Exception{

		int result = commentService.contextallcommentDeleteService(1000);

	}

	/*@Test
	public void 사진입력() throws Exception{

	MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

		 uploadfile ="dog.jpg";


		String result = photoBoardService.saveFile(uploadfile);


		photoBoardDto.setIp(photoBoardService.getIp(request));
		photoBoardDto.setFile(result);

		photoBoardService.contextWriteService(photoBoardDto);


	}*/



}
