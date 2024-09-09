package com.kagoji.atfarestfulapis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kagoji.atfarestfulapis.entity.User;
import com.kagoji.atfarestfulapis.repository.UserRepository;

@Service
public class UserServiceImplement implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> alluserlist(){
		return userRepository.findAll();
		
	}
	
}
