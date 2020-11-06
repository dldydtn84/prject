package com.ys.appler.controller;

import com.google.gson.JsonObject;
import com.ys.appler.dto.BoardDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;

@Slf4j
@Controller
public class RefboardController {

    private final String UPLOAD_PATH = "c:" + File.separator + "temp" + File.separator;


    @GetMapping("/refboard/list")
    public String list() {

        return "/refboard/list";
    }
    @GetMapping("/refboard/write")
    public String write() {

        return "/refboard/write";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(Locale locale, Model model) {
        log.info("Welcome home! The client locale is {}.", locale);

        return "insert";
    }

    @RequestMapping(value = "/refboard/fileupload",method = RequestMethod.POST)
    public String upload(MultipartFile uploadfile, Model model) throws IOException {


        log.info("파일 이름: {}", uploadfile.getOriginalFilename());
        log.info("파일 크기: {}", uploadfile.getSize());


        String result = saveFile(uploadfile);
        log.info(result);

        if(result !=null){ // 파일 저장 성공

            model.addAttribute("result", result);

        } else { // 파일 저장 실패

            model.addAttribute("result","fail");

        }

        return "redirect:/";

    }

    @RequestMapping(value = "/fileupload2", method = RequestMethod.POST)
    public String multiupload(@RequestParam("uploadfiles") MultipartFile[] file, Model model) throws IOException {

        log.info("Welcome multi file! The client locale is {}.", "Hello");

        String result = "";

        for(MultipartFile f : file){
            result += saveFile(f);
        }

        return "redirect:/";
    }


    private String saveFile(MultipartFile file) throws IOException{

        // 파일 이름 변경
        UUID uuid = UUID.randomUUID();
        String saveName = uuid + "_" + file.getOriginalFilename();

        log.info("saveName: {}",saveName);

        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        long filesize = file.getSize();
        // byte[] bytes = file.getBytes();

        //System.out.println("파일명:" + fileName);
        //System.out.println("컨텐츠 타입:" + contentType);
        //System.out.println("파일크기:" + filesize);

        // 저장할 File 객체를 생성(껍데기 파일)ㄴ
        File saveFile = new File(UPLOAD_PATH, saveName); // 저장할 폴더 이름, 저장할 파일 이름

        try {
            file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return saveName;
    }




    @GetMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
        log.info("파일업로드");
        JsonObject jsonObject = new JsonObject();
        log.info(String.valueOf(multipartFile));
        String fileRoot = "C:\\summernote_image\\";	//저장될 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        // 랜덤 UUID+확장자로 저장될 savedFileName
        String savedFileName = UUID.randomUUID() + extension;
        log.info("filename : "+savedFileName);
        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	// 실패시 저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }


}
