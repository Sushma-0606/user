package com.user.task.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.user.task.entity.PageModel;
import com.user.task.entity.User;
import com.user.task.entity.UserModel;
import com.user.task.request.PaginationRequestModel;

public interface UserService {

	User registeration(UserModel userModel );

	User loginUser(String userName, String password);

	Page<User> searchUser(String keyword, Pageable pageable);

	PageModel filter(UserModel userModel,PaginationRequestModel paginationRequestModel);
	
	User getUserById(Long userId);

}
