package com.kagoji.atfarestfulapis.filter;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.kagoji.atfarestfulapis.logger.CustomLogger;

import java.io.BufferedReader;
import java.io.IOException;


import org.slf4j.Logger;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccessLogFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger("RequestLogger");
	
	@Autowired
	CustomLogger customLogger;
	
	@Override
	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
			throws jakarta.servlet.ServletException, IOException {
		if (request.getRequestURI().toLowerCase().contains("/api")) {
	         try {
	             filterChain.doFilter(request, response);
	         }
	         finally {
	             String userAgent =getUserAgent(request);
	             String remoteAddress = getRemoteAddress(request);
	             
	             String logMessage = remoteAddress+"|"+request.getMethod()+"|"+getFullURL(request)+" |User-Agent:"+userAgent+"|Content-Type:"+response.getContentType()+"|HTTP:"+response.getStatus();
	             customLogger.customLogWrite("access_log","access_log", logMessage);
	             System.out.println("Accesslog write complete");

	         }
	     }
	     else {
	         filterChain.doFilter(request, response);
	     }
		
	}
	
	private String getRemoteAddress(jakarta.servlet.http.HttpServletRequest request) {
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
	

	private String getUserAgent(jakarta.servlet.http.HttpServletRequest request) {
	    return request.getHeader("User-Agent");
	}
	
	private String getFullURL(jakarta.servlet.http.HttpServletRequest request) {
	    StringBuilder requestURL = new StringBuilder(request.getRequestURL());
	    String queryString = request.getQueryString();
	    
	    if (queryString != null) {
	        requestURL.append("?").append(queryString);
	    }
	    
	    return requestURL.toString();
	}

}
