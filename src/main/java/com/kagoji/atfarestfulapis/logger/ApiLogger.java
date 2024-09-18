package com.kagoji.atfarestfulapis.logger;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kagoji.atfarestfulapis.model.AuthRequest;
import com.kagoji.atfarestfulapis.model.UserModel;

import java.io.InputStreamReader;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ApiLogger {
	
	@Autowired
	CustomLogger customLogger;
	
	public void apiLogWrite( HttpServletRequest request,Object requestBody,ResponseEntity<Object> apiresponse) throws IOException {
		
		
		String userAgent =getUserAgent(request);
        String remoteAddress = getRemoteAddress(request);
        String requestBodyJosn = convertToJson(requestBody);
        
        String logMessage = remoteAddress+"|"+request.getMethod()+"|"+getFullURL(request)+" |User-Agent:"+userAgent+"|RequestBody:"+requestBodyJosn+"| Response:"+apiresponse+"|HTTP:"+apiresponse.getStatusCodeValue();
		customLogger.customLogWrite("apiLog", "api_log", logMessage);
	}
	
	private String convertToJson(Object object) throws IOException {
        // Use your preferred JSON library, e.g., Jackson or Gson
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
	
	
	public void apiLogWrite( HttpServletRequest request,ResponseEntity<Object> apiresponse) throws IOException {
		
		
		String userAgent =getUserAgent(request);
        String remoteAddress = getRemoteAddress(request);
   
        
        String logMessage = remoteAddress+"|"+request.getMethod()+"|"+getFullURL(request)+" |User-Agent:"+userAgent+"|RequestBody:[]| Response:"+apiresponse+"|HTTP:"+apiresponse.getStatusCodeValue();
		customLogger.customLogWrite("apiLog", "api_log", logMessage);
	}
	
	
	private String getRemoteAddress(HttpServletRequest request) {
	    String ipAddress = request.getHeader("X-Forwarded-For");
	    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getRemoteAddr();
	    }

	    // Convert IPv6 loopback to IPv4 loopback
	    if ("0:0:0:0:0:0:0:1".equals(ipAddress)) {
	        ipAddress = "127.0.0.1";
	    }

	    return ipAddress;
	}
	

	private String getUserAgent(HttpServletRequest request) {
	    return request.getHeader("User-Agent");
	}
	
	private String getFullURL(HttpServletRequest request) {
	    StringBuilder requestURL = new StringBuilder(request.getRequestURL());
	    String queryString = request.getQueryString();
	    
	    if (queryString != null) {
	        requestURL.append("?").append(queryString);
	    }
	    
	    return requestURL.toString();
	}

//	public void apiLogWrite(HttpServletRequest request, UserModel userModel, ResponseEntity<Object> apiresponse) throws IOException{
//		// TODO Auto-generated method stub
//		String userAgent =getUserAgent(request);
//        String remoteAddress = getRemoteAddress(request);
//        String requestBody = convertToJson(userModel);
//        
//        String logMessage = remoteAddress+"|"+request.getMethod()+"|"+getFullURL(request)+" |User-Agent:"+userAgent+"|RequestBody:"+requestBody+"| Response:"+apiresponse+"|HTTP:"+apiresponse.getStatusCodeValue();
//		customLogger.customLogWrite("apiLog", "api_log", logMessage);
//		
//	}
	
}
