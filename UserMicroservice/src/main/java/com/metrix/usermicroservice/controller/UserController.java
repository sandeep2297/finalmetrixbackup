package com.metrix.usermicroservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrix.usermicroservice.model.User;
import com.metrix.usermicroservice.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	// For providing user google profile
	@GetMapping("/userprofile")
	public User getUserProfile(HttpServletRequest request) {
		User user = userService.loadByUsername(request);
		return user;
	}
}
