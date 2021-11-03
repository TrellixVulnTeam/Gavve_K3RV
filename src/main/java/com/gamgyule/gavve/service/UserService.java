package com.gamgyule.gavve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamgyule.gavve.dao.UserDAO;
import com.gamgyule.gavve.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	UserDAO userMapper;

	public List<UserDTO> getUserList() {
		return userMapper.getUserList();
	}
}
