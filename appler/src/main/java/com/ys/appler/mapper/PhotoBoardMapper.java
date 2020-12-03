package com.ys.appler.mapper;

import com.ys.appler.commons.paging.Criteria;
import com.ys.appler.dto.BoardDto;
import com.ys.appler.dto.PhotoBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PhotoBoardMapper {

    void contextWrite(PhotoBoardDto photoBoardDto);
    List<PhotoBoardDto> contextList(Criteria criteria);
    int totalcount();
    List<PhotoBoardDto> IndexPhotoList();
    PhotoBoardDto contextRead(int no);
    void contextDelete(int no);
    void contextModify(PhotoBoardDto photoBoardDto);
    void readcountUp(int reviewNo);

}
