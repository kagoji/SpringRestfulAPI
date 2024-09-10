package com.kagoji.atfarestfulapis.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kagoji.atfarestfulapis.entity.User;
import com.kagoji.atfarestfulapis.model.UserModel;
import com.kagoji.atfarestfulapis.repository.UserRepository;
import com.kagoji.atfarestfulapis.service.UserService;
//import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserServiceImplement implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	//private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> alluserlist(){
		return userRepository.findAll();
		
	}

	@Override
	public User createUser(UserModel userModel) {
		User user = new User();
		user.setUserName(userModel.getUserName());
		///user.setPassword(passwordE.encode(userModel.getPassword()));
		user.setPassword(userModel.getPassword());
		user.setRole(userModel.getRole());
		userRepository.save(user);
		return user;
	}

	@Override
	public String updateUser(Long userId, UserModel userModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> getUser(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}
	
}
