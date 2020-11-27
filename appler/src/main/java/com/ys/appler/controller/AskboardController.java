package com.ys.appler.controller;

import com.ys.appler.dto.BoardDto;
import com.ys.appler.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Slf4j
@Controller
public class AskboardController {
    @Autowired
    BoardService boardService;


    @GetMapping("/askboard/list")
    public String list(Model model) {


        List<BoardDto> bestcontextList = boardService.BestcontextListService();
        List<BoardDto> newcontextList = boardService.NewcontextListService();
        model.addAttribute("bestcontextList", bestcontextList);
        model.addAttribute("newcontextList", newcontextList);


        return "/askboard/list";
    }
}
