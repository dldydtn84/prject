package com.ys.appler.controller;



import com.ys.appler.service.NoticeBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/noticeboard")
public class NoticeController {

    @Autowired
    NoticeBoardService NoticeboardService;



    @GetMapping("/list")
    public String list() {

        return "/noticeboard/list";
    }
    @GetMapping("/write")
    public String write() {


        return "/noticeboard/write";
    }
    @PostMapping("/writepro")
    public String writepro(){



        NoticeboardService.contextWriteService();

        return "redirect:/noticeboard/list" ;
    }

    /*@PostMapping("/writepro")
    public String writepro(){

        return "" ;
    }

    @GetMapping("/modify")
    public String modify(Model model, @RequestParam("board") int board, @RequestParam("posts_no") int posts_no) {
        return "/board/modify";
    }

    @PostMapping("/modifypro")
    public String modifypro(@RequestParam("board") int boardnum, Model model,  ) {


        return ":/board/list?board="+boardnum;
    }


    @PostMapping("/deletePro")
    public String deletePro(Model model, @RequestParam("board") int board, @RequestParam("posts_no") int posts_no) {


        return "redirect:/board/list?board=" + board;
    }


    @GetMapping("/read")
    public String read(@RequestParam("board") int board, @RequestParam("posts_no") int posts_no, Model model) {





        return "//read";
    }
*/

}
