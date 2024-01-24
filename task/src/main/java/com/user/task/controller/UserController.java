package com.user.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.task.entity.User;
import com.user.task.entity.UserModel;
import com.user.task.helper.UserHelper;
import com.user.task.request.PaginationRequestModel;
import com.user.task.service.UserService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RequestMapping("/api/user")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

	@Autowired
	UserHelper userHelper;

	@Autowired
	UserService userService;

	@PostMapping("/registration")
	public UserModel registerUser(@RequestBody UserModel userModel) {
		return userHelper.convertToUserModel(userService.registeration(userModel));
	}

	@PostMapping("/login")
	public UserModel loginUser(@RequestParam String userName, @RequestParam String password) {
		return userHelper.convertToUserModel(userService.loginUser(userName, password));

	}

	@GetMapping(path = "/filter")
	public Object filter(UserModel userModel, PaginationRequestModel paginationRequestModel) {
		return userService.filter(userModel, paginationRequestModel);

	}
	
	@GetMapping("/{userId}")
	public UserModel getUserById(@PathVariable Long userId) {
	User user = userService.getUserById(userId);
	return userHelper.convertToUserModel(user);
	}

}
