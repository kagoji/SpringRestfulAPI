package com.kagoji.atfarestfulapis.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;



//@Component
//public class RequestLoggingFilter extends OncePerRequestFilter {
//
//    private static final Logger logger = LoggerFactory.getLogger("RequestLogger");
//    
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain) throws ServletException, IOException {
//    	
//    	System.out.println("Filter HTTP");
//        // Log the request details
//        logger.info("Request URL: " + request.getRequestURL());
//        logger.info("HTTP Method: " + request.getMethod());
//        logger.info("Request Headers: " + getHeaders(request));
//        logger.info("Request Body: " + getRequestBody(request));
//
//        // Continue with the filter chain
//        filterChain.doFilter(request, response);
//    }
//
//    private String getRequestBody(HttpServletRequest request) {
//        StringBuilder requestBody = new StringBuilder();
//        try (BufferedReader reader = request.getReader()) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                requestBody.append(line);
//            }
//        } catch (IOException e) {
//            logger.error("Failed to read the request body", e);
//        }
//        return requestBody.toString();
//    }
//
//    private String getHeaders(HttpServletRequest request) {
//        StringBuilder headers = new StringBuilder();
//        for (String headerName : java.util.Collections.list(request.getHeaderNames())) {
//            headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
//        }
//        return headers.toString();
//    }
//
//	
//}
