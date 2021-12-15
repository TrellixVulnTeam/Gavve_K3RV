package com.gamgyule.gavve.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BannerDTO {
    private String id;
    private String bannerIdx;
    private String bannerImg;
    private String bannerWord;
    private Date bannerStart;
    private Date bannerEnd;
    private String bannerLink;
    
    
    
}
