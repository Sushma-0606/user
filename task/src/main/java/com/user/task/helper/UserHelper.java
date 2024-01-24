package com.user.task.helper;

import org.springframework.stereotype.Component;

import com.user.task.entity.User;
import com.user.task.entity.UserModel;
import com.user.task.util.AESEncryptionDecryptionUtil;

@Component
public class UserHelper {

	public UserModel convertToUserModel(User user) {
		UserModel userModel = new UserModel();
		userModel.setUserName(user.getUserName());
		userModel.setEmailId(user.getEmailId());
		userModel.setPassword(AESEncryptionDecryptionUtil.decrypt(user.getPassword()));
		return userModel;

	}

	public User convertToUser(UserModel userModel) {
		User user = new User();
		user.setUserName(userModel.getUserName());
		user.setEmailId(userModel.getEmailId());
		user.setPassword(userModel.getPassword());
		return user;

	}
	
//	public UserModel convertToUserModel(User user) {
//        UserModel userModel = new UserModel();
//        userModel.setUsername(user.getUsername());
//        userModel.setEmail(user.getEmail());
//        userModel.setPassword(AESEncryptionDecryptionUtil.decrypt(user.getPassword(), null));
//        return userModel;
//    }
}
