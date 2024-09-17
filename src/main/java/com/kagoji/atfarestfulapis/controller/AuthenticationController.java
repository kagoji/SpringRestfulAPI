package com.kagoji.atfarestfulapis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.kagoji.atfarestfulapis.logger.CustomLogger;
import com.kagoji.atfarestfulapis.model.AuthRequest;
import com.kagoji.atfarestfulapis.response.ResponseHandler;


@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	CustomLogger customLogger;
	
	
	
	@PostMapping("/api/v1/getToken")
	public ResponseEntity<Object> getAccesstokenWithAuthentication(@RequestBody AuthRequest authRequest){
		
		try {
			
			String logMessage = authRequest.getUsername()+"|"+authRequest.getPassword();
            customLogger.customLogWrite("temp","trace_log", logMessage);
            //return ResponseHandler.responseBuilder("Logged in successfully", HttpStatus.OK, null);
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	        );
	        customLogger.customLogWrite("temp","trace_log", authentication.toString());
	        if (authentication.isAuthenticated()) {
	            return ResponseHandler.responseBuilder("Logged in successfully", HttpStatus.OK, null);
	        } else {
	            return ResponseHandler.responseBuilder("Username or password incorrect", HttpStatus.NOT_ACCEPTABLE, null);
	        }
	    } catch (Exception e) {
	        // Log the exception and return an appropriate response
	    	customLogger.customLogWrite("temp","trace_log",  e.getMessage());
	        return ResponseHandler.responseBuilder("Authentication failed", HttpStatus.UNAUTHORIZED, e.getMessage());
	    }
	}
}
