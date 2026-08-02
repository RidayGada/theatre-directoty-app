package com.theatre_directory.service;

import org.springframework.stereotype.Service;

import com.theatre_directory.dto.request.LoginRequest;
import com.theatre_directory.dto.request.SignupRequest;
import com.theatre_directory.dto.response.JwtResponse;
import com.theatre_directory.entity.User;
import com.theatre_directory.repository.UserRepository;
import com.theatre_directory.security.JwtUtils;

import com.theatre_directory.config.AppConstants;
import com.theatre_directory.enums.Role;
import com.theatre_directory.exception.EmailAlreadyExistsException;
import com.theatre_directory.exception.InvalidCredentialsException;
import com.theatre_directory.exception.InvalidManagerCodeException;

@Service
public class AuthService {

	private final UserRepository userRepository;
	
	private final JwtUtils jwtUtils;

	public AuthService(UserRepository userRepository, JwtUtils jwtUtils) {

		this.userRepository = userRepository;
		this.jwtUtils = jwtUtils;
	}
	
	public JwtResponse signup(SignupRequest request) {

	    if (userRepository.existsByEmail(request.getEmail())) {
	    	throw new EmailAlreadyExistsException("Email already exists");
	    }

	    if (request.getRole() == Role.MANAGER) {

	        if (!AppConstants.MANAGER_CODE.equals(request.getManagerCode())) {
	        	throw new InvalidManagerCodeException("Invalid manager code.");
	        }
	    }
	    
	    User user = new User();

	    user.setName(request.getName());
	    user.setEmail(request.getEmail());
	    user.setPassword(request.getPassword());
	    user.setRole(request.getRole());

	    user = userRepository.save(user);

	    String token = jwtUtils.generateToken(user);

	    return new JwtResponse(token);
	}
	
	public JwtResponse login(LoginRequest request) {

	    User user = userRepository.findByEmail(request.getEmail())
	            .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

	    if (!user.getPassword().equals(request.getPassword())) {
	        throw new InvalidCredentialsException("Invalid email or password");
	    }

	    String token = jwtUtils.generateToken(user);

	    return new JwtResponse(token);
	}
}
