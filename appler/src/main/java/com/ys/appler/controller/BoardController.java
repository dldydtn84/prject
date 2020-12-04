package com.ys.appler.controller;


import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.commons.paging.Pageing;
import com.ys.appler.config.auth.PrincipalDetails;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

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







    @GetMapping("/list")
    public String list(@RequestParam("board") int board, @RequestParam(value = "perPageNum", defaultValue = "15") int perPageNum,
                       @RequestParam(value = "page", defaultValue = "1") int page, Model model, BoardDto boardDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        int totalcount = boardService.selectListnoService(board);

        String boardCode = boardCode(board);
        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setPerPageNum(perPageNum);
        criteria.setBoardCode(boardCode);

        Pageing pageing = new Pageing();
        pageing.setCriteria(criteria);
        pageing.setTotalCount(totalcount);
        int start = pageing.getStartPage();








        List<BoardDto> contextlist = boardService.listPagingService(criteria);
        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();


if(principalDetails != null) {
    model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
}


        model.addAttribute("pageing", pageing);
        model.addAttribute("contextlist", contextlist);
        model.addAttribute("board", board);
        model.addAttribute("start", start);

        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);

        return "board/list";
    }


    @GetMapping("/write")
    public String write(@RequestParam("board") int boardnum, @ModelAttribute BoardDto boardDto, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        String boardcode = boardCode(boardnum);

        if(principalDetails != null) {
            model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
        }

        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);


        model.addAttribute("boardnum", boardnum);
        model.addAttribute("boardcode", boardcode);


        return "board/write";
    }

    @PostMapping("/writepro")
    public String writepro(@RequestParam("board_code") String board_code,
                           @RequestParam("boardnum") String boardnum,
                           @Valid BoardDto boardDto, Errors errors,
                          HttpServletRequest request,
                          Model model) {
        int boardpostno = boardService.postnoOneService(board_code);

        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("boardDto", boardDto);


            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = boardService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
                log.info("key : " +key);
                log.info("key2 : " +validatorResult.get(key));
            }

            return "board/write";
        }
        else {


            boardDto.setIp(boardService.getIp(request));


            boardDto.setPosts_no(boardpostno);
            boardService.contextWriteService(boardDto);


            return "redirect:/board/list?board=" + boardnum;
        }

    }

    @GetMapping("/modify")
    public String modify(Model model, @RequestParam("board") int board, @RequestParam("posts_no") int posts_no, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String boardcode = boardCode(board);
        BoardDto contextread = boardService.contextReadService(posts_no, board);

        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);
        if(principalDetails != null) {
            model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
        }


        model.addAttribute("contextread", contextread);
        model.addAttribute("board", board);
        model.addAttribute("boardcode", boardcode);
        model.addAttribute("posts_no", posts_no);


        return "board/modify";
    }

    @GetMapping("/modifypro")
    public String modifypro(@RequestParam("board") int boardnum, Model model, BoardDto boardDto) {
        boardService.contextUpdateService(boardDto);

        return "redirect:/board/list?board=" + boardnum;
    }


    @PostMapping("/deletePro")
    public String deletePro(@RequestParam("board") int board, @RequestParam("posts_no") int posts_no, HttpServletResponse response) throws Exception {
        /*log.info(String.valueOf(posts_no));*/
        boardService.contextDeleteService(board, posts_no);

        Cookie delCk = new Cookie("cookie" + posts_no, null);
        delCk.setMaxAge(0);
        response.addCookie(delCk);

        int result = commentService.contextallcommentDeleteService(posts_no);

        log.info("comment delete result" + result);

        return "redirect:/board/list?board=" + board;
    }


    @RequestMapping(value = "/read")
    public String read(@RequestParam(value = "board", defaultValue = "") int board, @RequestParam("posts_no") int posts_no, Model model, BoardDto boardDto, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {


        if(principalDetails != null) {
            model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
        }

        BoardDto contextread = boardService.contextReadService(posts_no, board);
        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);

        model.addAttribute("contextread", contextread);
        model.addAttribute("board", board);
        model.addAttribute("posts_no", posts_no);

        //게시판 시퀀스번호
        int boardno = contextread.getNo();


        //쿠키가져오기
        Cookie[] cookies = request.getCookies();

        // 새로운쿠키 생성하여 비교
        Cookie viewCookie = null;

        // 쿠키가 있을 경우
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                // Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌
                if (cookies[i].getName().equals("cookie" + posts_no)) {
                    viewCookie = cookies[i];
                }
            }
        }
        // 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
        if (viewCookie == null) {

            // 쿠키 생성(이름, 값)
            Cookie newCookie = new Cookie("cookie" + posts_no, "|" + posts_no + "|");
            // 쿠키 추가
            response.addCookie(newCookie);


            boardService.readcountUpService(boardno);
        }
        // viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
        else {
            // 쿠키 값 받아옴.
            String value = viewCookie.getValue();
        }


        return "board/read";
    }

    @RequestMapping(value = "/reading")
    public String reading(@RequestParam(value = "no") int no, Model model, BoardDto boardDto, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {


        if(principalDetails != null) {
            model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
        }

        BoardDto contextread = boardService.contextReadingService(no);


       int posts_no=  contextread.getPosts_no();
       String board_code =contextread.getBoard_code();
       int board_num = 0;
        if (board_code.equals("FB")) {
            board_num = 1;
        } else if (board_code.equals("QB")) {
            board_num = 2;
        } else if (board_code.equals("CB")) {
            board_num = 3;
        }

        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);

        model.addAttribute("contextread", contextread);
        model.addAttribute("board", board_num);
        model.addAttribute("posts_no", posts_no);

        //게시판 시퀀스번호
        int boardno = contextread.getNo();


        //쿠키가져오기
        Cookie[] cookies = request.getCookies();

        // 새로운쿠키 생성하여 비교
        Cookie viewCookie = null;

        // 쿠키가 있을 경우
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                // Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌
                if (cookies[i].getName().equals("cookie" + posts_no)) {
                    viewCookie = cookies[i];
                }
            }
        }
        // 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
        if (viewCookie == null) {

            // 쿠키 생성(이름, 값)
            Cookie newCookie = new Cookie("cookie" + posts_no, "|" + posts_no + "|");
            // 쿠키 추가
            response.addCookie(newCookie);


            boardService.readcountUpService(boardno);
        }
        // viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
        else {
            // 쿠키 값 받아옴.
            String value = viewCookie.getValue();
        }


        return "board/read";
    }

}
