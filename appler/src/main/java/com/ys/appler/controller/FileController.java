package com.ys.appler.controller;


import com.google.gson.JsonObject;
import com.ys.appler.dto.BoardDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Controller
public class FileController {

  @PostMapping(value = "/uploadSummernoteImageFile")
  @ResponseBody
  public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

    log.info("file : " + String.valueOf(multipartFile));

    String fileRoot = "C://summernote_image/";    //저장될 외부 파일 경로
    String originalFileName = multipartFile.getOriginalFilename();    //오리지날 파일명
    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자



    String savedFileName = UUID.randomUUID() + "_" + originalFileName;    //저장될 파일 명

    File targetFile = new File(fileRoot + savedFileName);

    try {
      InputStream fileStream = multipartFile.getInputStream();
      FileUtils.copyInputStreamToFile(fileStream, targetFile);    //파일 저장


    } catch (IOException e) {
      log.info("save file error");
      FileUtils.deleteQuietly(targetFile);    //저장된 파일 삭제

      e.printStackTrace();
    }
    log.info("savedFileName : " + savedFileName);

    return savedFileName;
  }
}

