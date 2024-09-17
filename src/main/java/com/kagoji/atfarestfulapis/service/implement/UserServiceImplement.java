package com.kagoji.atfarestfulapis.service.implement;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kagoji.atfarestfulapis.entity.User;
import com.kagoji.atfarestfulapis.exception.ApiThrowException;
import com.kagoji.atfarestfulapis.model.UserModel;
import com.kagoji.atfarestfulapis.repository.UserRepository;
import com.kagoji.atfarestfulapis.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserServiceImplement implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> alluserlist(){
		List<User> userList = userRepository.findAll();
		return userList;
		
	}

	@Override
	public User createUser(UserModel userModel) {
		User user = new User();
		user.setUserName(userModel.getUserName());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		user.setRole(userModel.getRole());
		System.out.println(user);
		userRepository.save(user);
		return user;
	}

	@Override
	public User updateUser(Long userId, UserModel userModel) {
		
		Optional<User> userUpdateOptional = userRepository.findById(userId);

	    if (userUpdateOptional.isPresent()) {
	    	User userUpdateInfo = userUpdateOptional.get();
	    	userUpdateInfo.setUserName(userModel.getUserName());
	    	userUpdateInfo.setRole(userModel.getRole());
			return userRepository.save(userUpdateInfo);
	    }else {
	    	return null;
	    }
		
	}

	@Override
	public Boolean deleteUser(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> userUpdateOptional = userRepository.findById(userId);
	    if (userUpdateOptional.isPresent()) {
	    	userRepository.deleteById(userId);
			return true;
	    }else {
	    	return false;
	    }
	}

	@Override
	public Optional<User> getUser(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> userInfo = userRepository.findById(userId);
		if(userInfo.isEmpty())
			throw new ApiThrowException("User not found");
		
		return userRepository.findById(userId);
	}
	
	
}
