package com.kagoji.atfarestfulapis.logger;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
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
      logger.info("Request URL: " + request.getRequestURL());
      logger.info("HTTP Method: " + request.getMethod());
      logger.info("Request Headers: " + getHeaders(request));
      logger.info("Request Body: " + getRequestBody(request));
      filterChain.doFilter(request, response);
      //private final logMessage = "url";
      //customLogger.customLogWrite("sum","sum", logMessage);
	}
	
  private String getRequestBody(jakarta.servlet.http.HttpServletRequest request) {
  StringBuilder requestBody = new StringBuilder();
  try (BufferedReader reader = request.getReader()) {
      String line;
      while ((line = reader.readLine()) != null) {
          requestBody.append(line);
      }
  } catch (IOException e) {
      logger.error("Failed to read the request body", e);
  }
  return requestBody.toString();
}

private String getHeaders(jakarta.servlet.http.HttpServletRequest request) {
  StringBuilder headers = new StringBuilder();
  for (String headerName : java.util.Collections.list(request.getHeaderNames())) {
      headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
  }
  return headers.toString();
}
	

}
