package com.ys.appler.controller;



import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.commons.paging.Pageing;
import com.ys.appler.config.auth.PrincipalDetails;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.NoticeBoardDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.NoticeBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/noticeboard")
public class NoticeController {

    @Autowired
    NoticeBoardService NoticeboardService;

@Autowired
    BoardService boardService;

    @GetMapping("/list") //search_option 차후 개선가능
    public String list(Model model, NoticeBoardDto noticeBoardDto, @RequestParam(value = "perPageNum", defaultValue = "15") int perPageNum,
                       @RequestParam(value = "page", defaultValue = "1") int page,  @RequestParam(defaultValue="all") String search_option,
                       @RequestParam(value = "keyword", defaultValue="") String keyword ,@AuthenticationPrincipal PrincipalDetails principalDetails ) {

        int totalcount = NoticeboardService.ListCountService(search_option,keyword);


        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setPerPageNum(perPageNum);
        criteria.setSearch_option(search_option);
        criteria.setKeyword(keyword);


        Pageing pageing = new Pageing();
        pageing.setCriteria(criteria);
        pageing.setTotalCount(totalcount);
        int start = pageing.getStartPage();

        if(principalDetails != null){
            model.addAttribute("Authority", principalDetails.getMemberDto().getAuthority());
            model.addAttribute("userid", principalDetails.getMemberDto().getUserid());

        }

        List<NoticeBoardDto> contextlist = NoticeboardService.listPagingService(criteria);

        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);

        model.addAttribute("pageing", pageing);
        model.addAttribute("contextlist", contextlist);
        model.addAttribute("start", start);

        model.addAttribute("keyword", keyword);


        return "/noticeboard/list";
    }

    @GetMapping(value = "/read")
    public String read(@RequestParam("no") int no, Model model, NoticeBoardDto noticeBoardDto, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(principalDetails != null){
            model.addAttribute("Authority", principalDetails.getMemberDto().getAuthority());
            model.addAttribute("userid", principalDetails.getMemberDto().getUserid());

        }

        NoticeBoardDto contextread = NoticeboardService.contextReadService(no);

        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);

        model.addAttribute("contextread", contextread);
        model.addAttribute("no", no);

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
                if (cookies[i].getName().equals("cookie" + no)) {
                    viewCookie = cookies[i];
                }
            }
        }
        // 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
        if (viewCookie == null) {

            // 쿠키 생성(이름, 값)
            Cookie newCookie = new Cookie("cookie" + no, "|" + no + "|");
            // 쿠키 추가
            response.addCookie(newCookie);


            NoticeboardService.readcountUpService(boardno);
        }
        // viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
        else {
            // 쿠키 값 받아옴.
            String value = viewCookie.getValue();
        }


        return "/noticeboard/read";
    }






@Secured("ROLE_ADMIN")
    @GetMapping("/write")
    public String write(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {

    if(principalDetails != null) {
        model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
    }


    List<BoardDto> bestcontextList = boardService.BestcontextListService();
    List<BoardDto> newcontextList = boardService.NewcontextListService();
    model.addAttribute("bestcontextList", bestcontextList);
    model.addAttribute("newcontextList", newcontextList);

        return "/noticeboard/write";
    }
    @PostMapping("/write")
    public String writepro(NoticeBoardDto noticeBoardDto, HttpServletRequest request,Model model) {

        noticeBoardDto.setIp(boardService.getIp(request));

        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);

        NoticeboardService.contextWriteService(noticeBoardDto);

        return "redirect:/noticeboard/list";
    }

    @GetMapping("/modify")
    public String modify(Model model,  @RequestParam("no") int no,@AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(principalDetails != null) {
            model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
        }


        NoticeBoardDto contextread = NoticeboardService.contextReadService(no);
        model.addAttribute("contextread", contextread);
        model.addAttribute("no", no);

        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);

        return "/noticeboard/modify";
    }

    @GetMapping("/modifypro")
    public String modifypro(Model model, NoticeBoardDto noticeBoardDto) {
        NoticeboardService.contextUpdateService(noticeBoardDto);

        return "redirect:/noticeboard/list";
    }

    @PostMapping("/deletePro")
    public String deletePro(Model model, @RequestParam("no") int no, HttpServletResponse response) throws Exception {
        NoticeboardService.contextDeleteService(no);

        Cookie delCk = new Cookie("cookie" + no, null);
        delCk.setMaxAge(0);
        response.addCookie(delCk);



        return "redirect:/noticeboard/list";
    }


}
