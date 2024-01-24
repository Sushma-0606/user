package com.user.task.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.user.task.entity.PageModel;
import com.user.task.entity.User;
import com.user.task.entity.UserModel;
import com.user.task.helper.UserHelper;
import com.user.task.repository.UserRepository;
import com.user.task.request.PaginationRequestModel;
import com.user.task.service.UserService;
import com.user.task.util.AESEncryptionDecryptionUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	

	@Autowired
	UserHelper userHelper;

	@Override
	public User registeration(UserModel userModel ) {
		User user = new User();
		// Encrypt the password before saving the database
		String encrypt = AESEncryptionDecryptionUtil.encrypt(userModel.getPassword());
		user.setPassword(encrypt);
		if(userRepository.existsByUserName(user.getUserName())) {
			throw new RuntimeException("User name already exists");
		}
		user.setEmailId(userModel.getEmailId());
		
		return userRepository.save(user);
	}

	@Override
	public User loginUser(String userName, String password) {
		User user = userRepository.findByUserName(userName);
		if (user != null) {
			try {
				// Decrypt the stored password and compare with the entered password
				String decryptedPassword = AESEncryptionDecryptionUtil.encrypt(user.getPassword());
				if (decryptedPassword.equals(password)) {
					return user;
				}
			} catch (Exception e) {
				// Handle decryption exception
				e.printStackTrace();
				throw new RuntimeException("Error decrypting password", e);
			}
		}
		return user;
		
	}

	@Override
	public Page<User> searchUser(String keyword, Pageable pageable) {
		return userRepository.findByUserNameContainingIgnoreCase(keyword, pageable);
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Id not found" + userId));
	}

	@Override
	public PageModel filter(UserModel userModel, PaginationRequestModel paginationRequestModel) {
	    Pageable pageable = createPageable(paginationRequestModel);
	    Page<User> page = userRepository.findAll(pageable);

	    List<UserModel> userModels = page.getContent().stream()
	            .map(user -> userHelper.convertToUserModel(user))
	            .collect(Collectors.toList());

	    return PageModel.builder()
	            .data(userModels)
	            .totalCount(page.getTotalElements())
	            .pageCount((long) page.getTotalPages())
	            .build();
	}

	private Pageable createPageable(PaginationRequestModel paginationRequestModel) {
	    return PageRequest.of(
	            paginationRequestModel.getPageNumber() != null ? paginationRequestModel.getPageNumber() : 8,
	            paginationRequestModel.getPageSize() != null ? paginationRequestModel.getPageSize() : 1
	    );
	}



}
