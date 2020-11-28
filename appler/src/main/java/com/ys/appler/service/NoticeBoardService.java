package com.ys.appler.service;

import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.NoticeBoardDto;
import com.ys.appler.mapper.NoticeBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeBoardService {

    @Autowired
    NoticeBoardMapper noticeBoardMapper;




    public void contextWriteService(NoticeBoardDto noticeBoardDto){


        noticeBoardMapper.contextWrite(noticeBoardDto);
    }


    public int ListCountService( ){


        return noticeBoardMapper.ListCount();
    }
    public List<NoticeBoardDto> listPagingService(Criteria criteria){



        List<NoticeBoardDto> result = noticeBoardMapper.listPaging(criteria);

        return result;
    }

    public NoticeBoardDto contextReadService(int no){

        NoticeBoardDto boardread = noticeBoardMapper.contextRead(no);
        return  boardread;
    }
    public void readcountUpService(int reviewNo){
        noticeBoardMapper.readcountUp(reviewNo);

    }
    public void contextUpdateService(NoticeBoardDto noticeBoardDto){
        noticeBoardMapper.contextUpdate(noticeBoardDto);

    }
    public void contextDeleteService(int no){

        Map<String, String> map = new HashMap<String, String>();
        map.put("no", String.valueOf(no));


        noticeBoardMapper.contextDelete(map);
    }
    public List<NoticeBoardDto> contextSearchService(String search){



        List<NoticeBoardDto> result=noticeBoardMapper.contextSearch(search);
        return result;
    }
}
