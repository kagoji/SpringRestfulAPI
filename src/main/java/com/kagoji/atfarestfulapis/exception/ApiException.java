package com.kagoji.atfarestfulapis.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class ApiException {
	
	
	private final String timestamp;
	private final String message;
	private final String throwable;
	private final String httpStatus;
	
	public ApiException(String message, Throwable throwable, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.throwable = throwable == null ? null : throwable.toString();;
		this.httpStatus = httpStatus.toString();
		this.timestamp = generateTimestamp();
	}

	public String getMessage() {
		return message;
	}

	public String getThrowable() {
		return throwable;
	}

	public String getHttpStatus() {
		return httpStatus;
	}
	
	private static String generateTimestamp() {
        // Using LocalDateTime to get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        
        // Optionally, format the timestamp using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

}
