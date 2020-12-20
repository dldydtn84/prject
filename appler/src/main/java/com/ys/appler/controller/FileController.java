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
 /*   File.separator + "home" + File.separator + "ubuntu" + File.separator + "app" + File.separator
        + "step1" + File.separator + "prject" + File.separator + "appler" + File.separator
        + "summernote" + File.separator;

  */
    String fileRoot = File.separator + "home" + File.separator + "ubuntu" + File.separator + "app" + File.separator
        + "step1" + File.separator + "prject" + File.separator + "appler" + File.separator
        + "summernote" + File.separator;

    String originalFileName = multipartFile.getOriginalFilename();
    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));



    String savedFileName = UUID.randomUUID() + "_" + originalFileName;

    File targetFile = new File(fileRoot + savedFileName);

    try {
      InputStream fileStream = multipartFile.getInputStream();
      FileUtils.copyInputStreamToFile(fileStream, targetFile);


    } catch (IOException e) {
      log.info("save file error");
      FileUtils.deleteQuietly(targetFile);
      e.printStackTrace();
    }
    log.info("savedFileName : " + savedFileName);

    return savedFileName;
  }
}

