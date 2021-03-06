package com.ys.appler.service;

import com.ys.appler.dto.QuestBoardDto;
import com.ys.appler.mapper.QuestBoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class QuestBoardService {

  @Autowired
  QuestBoardMapper questBoardMapper;

  private final String UPLOAD_PATH = "c:" + File.separator + "temp" + File.separator;

  public String saveFile(MultipartFile file) throws IOException {

    // 파일 이름 변경
    UUID uuid = UUID.randomUUID();
    String saveName = uuid + "_" + file.getOriginalFilename();

    log.info("saveName: {}", saveName);

    String fileName = file.getOriginalFilename();
    log.info("fileName : " + fileName);
    String contentType = file.getContentType();
    long filesize = file.getSize();

    // 저장할 File 객체를 생성(껍데기 파일)
    File saveFile = new File(UPLOAD_PATH, saveName); // 저장할 폴더 이름, 저장할 파일 이름
    log.info("saveFile : " + String.valueOf(saveFile));
    try {
      file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    return saveName;
  }


  public Map<String, String> validateHandling(Errors errors) {
    Map<String, String> validatorResult = new HashMap<>();

    for (FieldError error : errors.getFieldErrors()) {
      String validKeyName = String.format("valid_%s", error.getField());
      validatorResult.put(validKeyName, error.getDefaultMessage());
    }

    return validatorResult;
  }


  public void contextWriteService(QuestBoardDto questBoardDto) {

    questBoardMapper.contextWrite(questBoardDto);
  }


}
