package com.ys.appler.controller;

import com.ys.appler.dto.FreeboardDto;
import com.ys.appler.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;


    @GetMapping("/list")
    public String list(@RequestParam("board") String name , Model model){

        List<FreeboardDto> contextlist = boardService.contextList(name);
        model.addAttribute("contextlist", contextlist);
        return "/board/list";
    }
    @GetMapping("/questionboard")
    public String question(Model model){
        //model.addAttribute("data","hello");
        return "/board/questionBoard";
    }
    @GetMapping("/certifficationboard")
    public String certiffication(Model model){
        //model.addAttribute("data","hello");
        return "/board/certifficationBoard";
    }

    @GetMapping("/write")
    public String write(Model model){
        //model.addAttribute("data","hello");
        return "/board/write";
    }
    @GetMapping("/writepro")
    public String writepro(Model model){
        //model.addAttribute("data","hello");
        return "/board/writepro";
    }

}
