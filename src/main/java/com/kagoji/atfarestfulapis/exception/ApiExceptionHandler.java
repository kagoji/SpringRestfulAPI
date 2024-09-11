package com.kagoji.atfarestfulapis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(ApiThrowException.class)
	public ResponseEntity<Object> handleApiThrowException (ApiThrowException apiThrowException){
		
		ApiException apiException = new ApiException(
				apiThrowException.getMessage(),
				apiThrowException.getCause(),
				HttpStatus.NOT_FOUND				
			);
		return new ResponseEntity<>(apiException,HttpStatus.NOT_FOUND);
	}

}
