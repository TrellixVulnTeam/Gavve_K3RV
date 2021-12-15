package com.gamgyule.gavve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gamgyule.gavve.dto.BannerDTO;

@Mapper
public interface BannerDAO {
    List<BannerDTO> getBannerList();
}
