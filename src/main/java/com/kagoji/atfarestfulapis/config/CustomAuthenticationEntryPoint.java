package com.kagoji.atfarestfulapis.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.kagoji.atfarestfulapis.exception.ApiThrowException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws IOException, ServletException {
		// Create a JSON response with structure similar to your example
        String jsonResponse = "{"
            + "\"data\": [],"
            + "\"httpStatus\": 401,"
            + "\"message\": \"User not found or unauthorized access\","
            + "\"timestamp\": \"" + generateTimestamp() + "\""
            + "}";

        // Write the JSON response
        response.getOutputStream().println(jsonResponse);
		
	}
	
	private static String generateTimestamp() {
        // Using LocalDateTime to get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        
        // Optionally, format the timestamp using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
