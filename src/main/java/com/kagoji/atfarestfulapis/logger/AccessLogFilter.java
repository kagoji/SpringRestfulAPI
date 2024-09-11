package com.kagoji.atfarestfulapis.logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class AccessLogFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AccessLogFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if necessary
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Log request details
        logger.info("Request: " + httpRequest.getMethod() + " " + httpRequest.getRequestURI());

        // Proceed with the next filter in the chain
        chain.doFilter(request, response);

        // Log response details
        logger.info("Response: " + httpResponse.getStatus());
    }

    @Override
    public void destroy() {
        // Cleanup code, if necessary
    }
}

