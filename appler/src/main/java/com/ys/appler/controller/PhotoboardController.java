package com.ys.appler.controller;


import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.commons.paging.Pageing;
import com.ys.appler.config.auth.PrincipalDetails;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.PhotoBoardDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.PhotoBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/photoboard")
public class PhotoboardController {
    @Autowired
    PhotoBoardService photoBoardService;

    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "perPageNum", defaultValue = "9") int perPageNum,
                       @RequestParam(value = "page", defaultValue = "1") int page) {

        int totalcount = photoBoardService.totalcountService();

        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setPerPageNum(perPageNum);

        Pageing pageing = new Pageing();
        pageing.setCriteria(criteria);
        pageing.setTotalCount(totalcount);
        int start = pageing.getStartPage();


        List<PhotoBoardDto> contextlist = photoBoardService.contextListService(criteria);

        model.addAttribute("contextlists", contextlist);
        model.addAttribute("pageing", pageing);
        model.addAttribute("start", start);


        return "photoboard/list";
    }

    @GetMapping("/write")
    public String write(Model model) {
        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);

        return "photoboard/write";
    }

    @RequestMapping(value = "/writepro", method = RequestMethod.POST)
    public String writepro(@RequestParam("uploadfile") MultipartFile uploadfile, Model model, PhotoBoardDto photoBoardDto, HttpServletRequest request) throws IOException {


        log.info("파일 이름: {}", uploadfile.getOriginalFilename());
        log.info("파일 크기: {}", uploadfile.getSize());

        String result = photoBoardService.saveFile(uploadfile);


        log.info("save : " + result);

        photoBoardDto.setIp(photoBoardService.getIp(request));
        photoBoardDto.setFile(result);

        photoBoardService.contextWriteService(photoBoardDto);


        if (result != null) { // 파일 저장 성공

            model.addAttribute("result", result);

        } else { // 파일 저장 실패

            model.addAttribute("result", "fail");

        }

        return "redirect:/photoboard/list";

    }

    @GetMapping("/read")
    public String read(@RequestParam("no") int no, Model model, PhotoBoardDto photoBoardDto, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        PhotoBoardDto contextread = photoBoardService.contextReadService(no);
        model.addAttribute("contextread", contextread);

        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);
        if (principalDetails != null) {
            model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
        }


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


            photoBoardService.readcountUpService(no);
        }
        // viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
        else {
            // 쿠키 값 받아옴.
            String value = viewCookie.getValue();
        }
        return "photoboard/read";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("no") int no, Model model, PhotoBoardDto photoBoardDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        PhotoBoardDto contextread = photoBoardService.contextReadService(no);
        model.addAttribute("contextread", contextread);

        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);
        if (principalDetails != null) {
            model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
        }

        return "photoboard/modify";
    }

    @PostMapping("/modify")
    public String modifypro(@RequestParam("no") int no, MultipartFile uploadfile, PhotoBoardDto photoBoardDto, HttpServletRequest request) throws IOException {
        String result = photoBoardService.saveFile(uploadfile);

        photoBoardDto.setIp(photoBoardService.getIp(request));
        photoBoardDto.setFile(result);

        photoBoardService.contextModifyService(photoBoardDto);


        return "redirect:/photoboard/read?no=" + no;
    }

    @PostMapping("/deletePro")
    public String deletePro(@RequestParam("no") int no) {

        photoBoardService.contextDeleteService(no);


        return "redirect:/photoboard/list";
    }
}
