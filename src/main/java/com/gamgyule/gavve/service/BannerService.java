package com.gamgyule.gavve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamgyule.gavve.dao.BannerDAO;
import com.gamgyule.gavve.dto.BannerDTO;

@Service
public class BannerService {

	@Autowired
	BannerDAO bannerMapper;

	public List<BannerDTO> getBannerList() {
		return bannerMapper.getBannerList();
	}
}
