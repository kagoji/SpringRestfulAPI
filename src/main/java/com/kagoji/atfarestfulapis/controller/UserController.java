package com.kagoji.atfarestfulapis.controller;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kagoji.atfarestfulapis.entity.User;
import com.kagoji.atfarestfulapis.model.UserModel;
import com.kagoji.atfarestfulapis.response.ApiResponse;
import com.kagoji.atfarestfulapis.response.ResponseHandler;
import com.kagoji.atfarestfulapis.service.UserService;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	/**
	   * Gets all user
	   *
	   * @return user list
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.alluserlist();
		
	}
	
	
	/**
	   * Gets users by id.
	   *
	   * @param userId the user id
	   * @return response
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	@GetMapping("/users/{userId}")	
	public ResponseEntity<Object> getUserinfo(@PathVariable("userId") Long userId) {
		
		Optional<User> userOptional= userService.getUser(userId);
		
		
		if(userOptional.isPresent()) {
			User userinfo = userOptional.get();
			return ResponseHandler.responseBuilder("user Details are given here",
	                HttpStatus.OK, userinfo);
	
		}else {
			return ResponseHandler.responseBuilder("user not found",
	                HttpStatus.NOT_FOUND, null);
		}
	}
	
	
	/**
	   * Create User.
	   *
	 
	   * @return response
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	@PostMapping("/users/create")
	public ResponseEntity<Object> createUser(@RequestBody UserModel userModel){
		User createdUser = userService.createUser(userModel);
		
		if(createdUser != null) {
			return ResponseHandler.responseBuilder("user has been created",
	                HttpStatus.OK, createdUser);
		}else {
			return ResponseHandler.responseBuilder("user not created",
	                HttpStatus.NOT_FOUND, null);
		}
		
	}
	
	
	
}
