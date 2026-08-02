package com.theatre_directory.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre_directory.dto.request.LoginRequest;
import com.theatre_directory.dto.request.SignupRequest;
import com.theatre_directory.dto.response.JwtResponse;
import com.theatre_directory.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/signup")
    public JwtResponse signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
    
}
