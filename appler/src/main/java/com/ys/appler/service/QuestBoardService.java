package com.ys.appler.service;

import com.ys.appler.dto.QuestBoardDto;
import com.ys.appler.mapper.QuestBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuestBoardService {

    @Autowired
    QuestBoardMapper questBoardMapper;

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }


    public void contextWriteService(QuestBoardDto questBoardDto){


        questBoardMapper.contextWrite(questBoardDto);
    }

}
