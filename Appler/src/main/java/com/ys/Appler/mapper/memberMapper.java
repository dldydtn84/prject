package com.ys.Appler.mapper;

import com.ys.Appler.dto.memberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;



@Mapper
public interface memberMapper {

   public memberDto selectmember();


}
/* public void insertMember (memberDto member);
    public void updateMember (memberDto member);
    public void deleteMember (String Id);
   public memberDto selectOneMember (String Id);
    public List<memberDto> selectAllMember();*/