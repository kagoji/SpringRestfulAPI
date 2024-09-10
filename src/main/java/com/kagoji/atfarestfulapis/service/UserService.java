package com.kagoji.atfarestfulapis.service;


import java.util.List;
import java.util.Optional;

import com.kagoji.atfarestfulapis.entity.User;
import com.kagoji.atfarestfulapis.model.UserModel;

public interface UserService {
	List<User> alluserlist();
	User createUser(UserModel userModel);
	public String updateUser(Long userId,UserModel userModel);
	public String deleteUser(Long userId);
	Optional<User> getUser(Long userId);
	
}
