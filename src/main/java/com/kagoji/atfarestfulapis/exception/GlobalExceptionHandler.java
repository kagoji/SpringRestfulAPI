package com.kagoji.atfarestfulapis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // Catch any unhandled exceptions
    public ResponseEntity<Object> handleInternalServerError(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        
     // Capture detailed error information
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String stackTrace = sw.toString();

        // Customizing the response
        data.put("error", ex.getMessage());
        data.put("exceptionType", ex.getClass().getName());
        data.put("stackTrace", stackTrace);
        response.put("data", data);
        response.put("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("message", "Internal Server Error");
        response.put("timestamp", generateTimestamp());

        // Return a JSON response with 500 status
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private static String generateTimestamp() {
        // Using LocalDateTime to get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        
        // Optionally, format the timestamp using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}

