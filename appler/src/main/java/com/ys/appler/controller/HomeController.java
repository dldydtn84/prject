package com.ys.appler.controller;

import com.ys.appler.config.auth.PrincipalDetails;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.MemberDto;
import com.ys.appler.dto.PhotoBoardDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.MemberService;
import com.ys.appler.service.PhotoBoardService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

   /* @Autowired
    MemberService memberService;*/

  @Autowired
  BoardService boardService;

  @Autowired
  PhotoBoardService photoBoardService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @GetMapping("/")
  public String index(Model model, HttpSession session,
      @AuthenticationPrincipal PrincipalDetails principalDetails, BoardDto boardDto) {

    if (principalDetails == null) {

    } else {
      model.addAttribute("nickname", principalDetails.getMemberDto().getNickname());
      model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
    }

    List<BoardDto> fbcontextList = boardService.IndexContextListService("FB");
    List<BoardDto> qbcontextList = boardService.IndexContextListService("QB");
    List<BoardDto> cbcontextList = boardService.IndexContextListService("CB");

    List<PhotoBoardDto> photoList = photoBoardService.IndexPhotoListService();

    List<BoardDto> bestcontextList = boardService.BestcontextListService();
    List<BoardDto> newcontextList = boardService.NewcontextListService();

    model.addAttribute("fbcontextList", fbcontextList);
    model.addAttribute("qbcontextList", qbcontextList);
    model.addAttribute("cbcontextList", cbcontextList);
    model.addAttribute("photoList", photoList);

    model.addAttribute("bestcontextList", bestcontextList);
    model.addAttribute("newcontextList", newcontextList);

    return "index";
  }

  @GetMapping("/admin/layout/default")
  public String defaults(Model model) {
    //model.addAttribute("data","hello");
    return "admin/layout/default";
  }


}
