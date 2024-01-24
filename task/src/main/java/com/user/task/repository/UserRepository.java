package com.user.task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.task.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Page<User> findByUserNameContainingIgnoreCase(String keyword, Pageable pageable);

	User findByUserName(String userName);
	
	boolean existsByUserName(String userName);

	
}
