package com.kagoji.atfarestfulapis.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(
            String message, HttpStatus httpStatus, Object responseObject
    )
    {
    	
    	//response mapping
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", generateTimestamp());
        response.put("data", responseObject);
        response.put("message", message);
        response.put("httpStatus", httpStatus.value());
        

        return new ResponseEntity<>(response, httpStatus);
    }
    
    private static String generateTimestamp() {
        // Using LocalDateTime to get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        
        // Optionally, format the timestamp using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}