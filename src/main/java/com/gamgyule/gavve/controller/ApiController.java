package com.gamgyule.gavve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamgyule.gavve.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/get")
	public String Home() {
		return userService.getUserList().toString();
	}

}
