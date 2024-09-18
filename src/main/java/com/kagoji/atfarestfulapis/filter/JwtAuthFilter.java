package com.kagoji.atfarestfulapis.filter;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kagoji.atfarestfulapis.config.AuthUserDetailsService;
import com.kagoji.atfarestfulapis.config.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	AuthUserDetailsService authUserDetailsService;
	
	@Autowired
    private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		
		try {
            // JWT validation logic (e.g., extract the token and validate it)
			String authHeader = request.getHeader("Authorization");
	        String token = null;
	        String username = null;

	     // Extract token from Authorization header
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7); // Remove "Bearer " prefix

                // Extract username from the token using JwtService
                username = jwtService.extractUsername(token);
            }

            // Validate token and set authentication if username is not null and no other authentication is set
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);

                // Validate the token
                if (jwtService.validateToken(token, userDetails)) {
                    // Set authentication in the SecurityContext
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // Continue with the filter chain
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException ex) {
            // Handle expired token exception
            handleExpiredJwtException(response, ex);

        } catch (Exception ex) {
            // Handle other exceptions (invalid token, etc.)
            handleJwtException(response, ex);
        }
		
		
	}

	private void handleJwtException(HttpServletResponse response, Exception ex) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        
        String jsonResponse = "{"
                + "\"data\": [],"
                + "\"httpStatus\": 401,"
                + "\"message\": \"Invalid JWT token\","
                + "\"timestamp\": \"" + generateTimestamp() + "\""
                + "}";

        // Send a custom JSON response
        response.getWriter().write(jsonResponse);
		
	}

	private void handleExpiredJwtException(HttpServletResponse response, ExpiredJwtException ex) throws IOException {
		// Set the response status to 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        
        String jsonResponse = "{"
                + "\"data\": {"
                + "\"expiredAt\": \"" + ex.getClaims().getExpiration() + "\""
                + "},"
                + "\"httpStatus\": 401,"
                + "\"message\": \"JWT token has expired\","
                + "\"timestamp\": \"" + generateTimestamp() + "\""
                + "}";

        // Send a custom JSON response
        response.getWriter().write(jsonResponse);
		
	}
	
	private static String generateTimestamp() {
        // Using LocalDateTime to get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        
        // Optionally, format the timestamp using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
	
	
	

}
