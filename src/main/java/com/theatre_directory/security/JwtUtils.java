package com.theatre_directory.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import com.theatre_directory.config.AppConstants;
import com.theatre_directory.entity.User;

import io.jsonwebtoken.security.Keys;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import io.jsonwebtoken.Claims;

@Component
public class JwtUtils {
	
	
	private final Key key = Keys.hmacShaKeyFor(
            AppConstants.JWT_SECRET.getBytes());
	
	
	public String generateToken(User user) {
		
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("userId", user.getId())
				.claim("role", user.getRole().name())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()
                        + AppConstants.JWT_EXPIRATION))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	private Claims getClaims(String token) {

	    return Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}

	
    public String extractEmail(String token) {

        return getClaims(token).getSubject();
    }

    
    public Long extractUserId(String token) {

        return getClaims(token).get("userId", Long.class);
    }

    
    public String extractRole(String token) {

        return getClaims(token).get("role", String.class);
    }
	
    
    public boolean isExpired(String token) {

        Date expiration = getClaims(token).getExpiration();

        return expiration.before(new Date());
    }
    
    
    public boolean validateToken(String token) {

        try {
            return !isExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
