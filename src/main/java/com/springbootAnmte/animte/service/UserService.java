package com.springbootAnmte.animte.service;

import java.util.List;

import com.springbootAnmte.animte.entity.User;

import jakarta.validation.Valid;

public interface UserService {

	User findUserByEmail(String email);

	void saveUser(@Valid User user);

	List<User> findAllUsers();

	void saveAdmin(@Valid User admin);
	
	Long findIdByEmail(String email);

	User getUserById(Long userId);  
	
	void updateResetPasswordToken(String token, String email);
	
	User getByResetPasswordToken(String token);
	
	void updatePassword(User user, String newPassword);
}
