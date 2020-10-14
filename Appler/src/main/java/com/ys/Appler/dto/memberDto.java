package com.ys.Appler.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("member")
@Getter @Setter
public class memberDto {
    private String seq;
    private String name;
    private String country;

}
