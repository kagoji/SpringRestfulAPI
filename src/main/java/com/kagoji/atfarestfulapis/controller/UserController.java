package com.kagoji.atfarestfulapis.controller;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kagoji.atfarestfulapis.entity.User;
import com.kagoji.atfarestfulapis.exception.ApiThrowException;
import com.kagoji.atfarestfulapis.model.UserModel;
import com.kagoji.atfarestfulapis.response.ResponseHandler;
import com.kagoji.atfarestfulapis.service.UserService;


@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	
	/**
	   * Gets all user
	   *
	   * @return user list
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	
	@GetMapping("/users")
	public ResponseEntity<Object> getAllUsers(){
		List <User> userList = userService.alluserlist();
		System.out.println("LIST Controller");
		if(!userList.isEmpty()) {
			return ResponseHandler.responseBuilder("All user list", HttpStatus.OK, userList);
		}else {
			return ResponseHandler.responseBuilder("No data available", HttpStatus.OK, null);
		}
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
		User userinfo = userOptional.get();
		
		return ResponseHandler.responseBuilder("user Details are given here",
                HttpStatus.OK, userinfo);
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
	
	/**
	   * Gets update by id.
	   *
	   * @param userId the user id, UserModel 
	   * @return response
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	
	@PutMapping("/users/update/{userId}")
	public ResponseEntity<Object> updateUserEntity(@PathVariable("userId") Long userId,@RequestBody UserModel userModel){
		User updateUser = userService.updateUser(userId, userModel);
		if(updateUser != null) {
			return ResponseHandler.responseBuilder("user has been updated",
	                HttpStatus.OK, updateUser);
		}else {
			return ResponseHandler.responseBuilder("user not found",
	                HttpStatus.NOT_FOUND, null);
		}
	}
	
	
	/**
	   * Delete by id.
	   *
	   * @param userId the user id 
	   * @return response
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	
	@DeleteMapping("/users/delete/{userId}")	
	public ResponseEntity<Object> deleteUserInfo(@PathVariable("userId") Long userId) {
		
		Boolean confirmation = userService.deleteUser(userId);
		
		if(confirmation) {
			return ResponseHandler.responseBuilder("user has been removed",
	                HttpStatus.OK, confirmation);
		}else {
			return ResponseHandler.responseBuilder("user not found",
	                HttpStatus.NOT_FOUND, confirmation);
		}
	}
	
}
