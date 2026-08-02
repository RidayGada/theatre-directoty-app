package com.theatre_directory.security;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.theatre_directory.config.AppConstants;
import com.theatre_directory.enums.Role;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter implements Filter{

	
	private final JwtUtils jwtUtils;
	
	private final UserContext userContext;
	
	
	public AuthFilter(JwtUtils jwtUtils, UserContext userContext) {
		this.jwtUtils = jwtUtils;
		this.userContext = userContext;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpServletResponse res = (HttpServletResponse) response;
		
		String authHeader = req.getHeader(AppConstants.AUTH_HEADER);
		
		
		if(authHeader == null || !authHeader.startsWith(AppConstants.TOKEN_PREFIX)) {
			res.setStatus(
			        HttpServletResponse.SC_UNAUTHORIZED
			    );

			    res.getWriter().write("Missing token");

			    return;
		}
		
		
		String token = authHeader.substring(AppConstants.TOKEN_PREFIX.length());
		
		if(!jwtUtils.validateToken(token)) {
			
		    res.setStatus(
			        HttpServletResponse.SC_UNAUTHORIZED
			    );

			    res.getWriter().write("Invalid token");

			    return;
		}
		
		// next two lines store the user id in user context, after extracting it from jwtutil
		// by extracting it using claims
		Long userId = jwtUtils.extractUserId(token);
		
		userContext.setCurrentUserId(userId);
	
		Role role = Role.valueOf(jwtUtils.extractRole(token));
		
		userContext.setCurrentUserRole(role);
		
		System.out.println("Extracted User ID : " + userId);
		System.out.println("Extracted Role    : " + role);
		
		chain.doFilter(req, res);
 	}
	
	
}
