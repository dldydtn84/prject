package com.ys.appler.controller;


import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.commons.paging.Pageing;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.PhotoBoardDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.PhotoBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public String list(Model model , @RequestParam(value = "perPageNum", defaultValue = "9") int perPageNum,
                       @RequestParam(value = "page", defaultValue = "1") int page) {

        int totalcount = photoBoardService.totalcountService();
        log.info("totalcount : "+String.valueOf(totalcount));

        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setPerPageNum(perPageNum);
        //criteria.setBoardCode(boardCode);

        Pageing pageing = new Pageing();
        pageing.setCriteria(criteria);
        pageing.setTotalCount(totalcount);
        int start = pageing.getStartPage();







      List<PhotoBoardDto> contextlist = photoBoardService.contextListService(criteria);
      log.info("context :"+String.valueOf(contextlist));


      model.addAttribute("contextlists",contextlist);
      model.addAttribute("pageing", pageing);
      model.addAttribute("start", start);


        return "/photoboard/list";
    }
    @GetMapping("/write")
    public String write(Model model) {
        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);

        return "/photoboard/write";
    }

    @RequestMapping(value = "/writepro",method = RequestMethod.GET)
    public String writepro(MultipartFile uploadfile, Model model , PhotoBoardDto photoBoardDto, HttpServletRequest request ) throws IOException {


        log.info("파일 이름: {}", uploadfile.getOriginalFilename());
        log.info("파일 크기: {}", uploadfile.getSize());

        String result = photoBoardService.saveFile(uploadfile);


        log.info("save : "+result);

        log.info("subject : "+photoBoardDto.getSubject());
        log.info("contents : "+photoBoardDto.getContents());


        photoBoardDto.setIp(photoBoardService.getIp(request));
         photoBoardDto.setFile(result);

        photoBoardService.contextWriteService(photoBoardDto);


        if(result !=null){ // 파일 저장 성공

            model.addAttribute("result", result);

        } else { // 파일 저장 실패

            model.addAttribute("result","fail");

        }

        return "redirect:/photoboard/list";

    }
    @GetMapping("/read")
    public String read(@RequestParam("no") int no, Model model, PhotoBoardDto photoBoardDto) {

        PhotoBoardDto contextread = photoBoardService.contextReadService(no);

        model.addAttribute("contextread", contextread);

        return "/photoboard/read";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("no") int no, Model model, PhotoBoardDto photoBoardDto) {

        PhotoBoardDto contextread = photoBoardService.contextReadService(no);

        model.addAttribute("contextread", contextread);

        return "/photoboard/modify";
    }
    @PostMapping("/deletePro")
    public String deletePro(@RequestParam("no") int no) {

        photoBoardService.contextDeleteService(no);


        return "redirect:/photoboard/list";
    }









   /* @RequestMapping(value = "/fileupload2", method = RequestMethod.POST)
    public String multiupload(@RequestParam("uploadfiles") MultipartFile[] file, Model model) throws IOException {

        log.info("Welcome multi file! The client locale is {}.", "Hello");

        String result = "";

        for(MultipartFile f : file){
            result += saveFile(f);
        }

        return "redirect:/";
    }*/







}
