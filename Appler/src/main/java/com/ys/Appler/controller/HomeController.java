package com.ys.Appler.controller;

import com.ys.Appler.dto.memberDto;

import com.ys.Appler.service.memberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private memberService memberService;

    @GetMapping("index")
    public String index(Model model){
        //model.addAttribute("data","hello");
        return "index";
    }
    @GetMapping("/admin/layout/default")
    public String defaults(Model model){
        //model.addAttribute("data","hello");
        return "/admin/layout/default";
    }

    @GetMapping("selectable")
    public String selectable(Model model) throws Exception {
    memberDto member = memberService.onemember();
        model.addAttribute("member", member);
        return "selectable";
    }


}
