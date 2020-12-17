package com.ys.appler.controller;

import com.ys.appler.config.auth.PrincipalDetails;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.MemberDto;
import com.ys.appler.dto.QuestBoardDto;
import com.ys.appler.service.BoardService;
import com.ys.appler.service.QuestBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/questboard")
@Controller
public class QuestBoardController {

  @Autowired
  BoardService boardService;

  @Autowired
  QuestBoardService questBoardService;


  @GetMapping("/write")
  public String write(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
    if (principalDetails != null) {
      model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
    }
    List<BoardDto> bestcontextList = boardService.BestcontextListService();
    List<BoardDto> newcontextList = boardService.NewcontextListService();
    model.addAttribute("bestcontextList", bestcontextList);
    model.addAttribute("newcontextList", newcontextList);
    return "questboard/write";
  }

  @PostMapping("/write")
  public String writepro(@Valid QuestBoardDto questBoardDto, MultipartFile uploadfile,
      Errors errors, Model model, HttpServletRequest request) throws IOException {

    if (errors.hasErrors()) {
      // 회원가입 실패시, 입력 데이터를 유지
      model.addAttribute("questBoardDto", questBoardDto);

      // 유효성 통과 못한 필드와 메시지를 핸들링
      Map<String, String> validatorResult = questBoardService.validateHandling(errors);
      for (String key : validatorResult.keySet()) {
        model.addAttribute(key, validatorResult.get(key));
        log.info("key : " + key);
        log.info("key2 : " + validatorResult.get(key));
      }

      return "questboard/write";
    } else {

      /* questBoardDto.setFile(questBoardService.saveFile(uploadfile));*/
      questBoardDto.setIp(boardService.getIp(request));
      String phone = questBoardDto.getPnum1() + questBoardDto.getPnum2() + questBoardDto.getPnum3();
      questBoardDto.setPhone(phone);

      questBoardService.contextWriteService(questBoardDto);

      return "redirect:/questboard/writesussce?email=" + questBoardDto.getEmail();
    }

  }

  @GetMapping("/writesussce")
  public String writesussce(@RequestParam("email") String email, Model model,
      @AuthenticationPrincipal PrincipalDetails principalDetails) {
    if (principalDetails != null) {
      model.addAttribute("userid", principalDetails.getMemberDto().getUserid());
    }
    List<BoardDto> bestcontextList = boardService.BestcontextListService();
    List<BoardDto> newcontextList = boardService.NewcontextListService();
    model.addAttribute("bestcontextList", bestcontextList);
    model.addAttribute("newcontextList", newcontextList);
    model.addAttribute("email", email);
    return "questboard/writesussce";
  }

}
