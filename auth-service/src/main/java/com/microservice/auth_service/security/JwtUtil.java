package com.microservice.auth_service.security;

import java.util.Date;

import javax.crypto.SecretKey;

import com.microservice.auth_service.entity.Role;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	private static final String SECRET_KEY = "mySuperSecretJwtKeyForRestaurantManagementSystem2026";

	private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	public static String extractUsername(String token) {

		return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload().getSubject();
	}

	public static String extractRole(String token) {

		return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload().get("role", String.class);
	}
	public static String generateToken(
	        String username,
	        Role role) {

	    return Jwts.builder()
	            .subject(username)
	            .claim("role", role.name())
	            .issuedAt(new Date())
	            .expiration(
	                new Date(
	                    System.currentTimeMillis()
	                    + 86400000
	                )
	            )
	            .signWith(KEY)
	            .compact();
	}
}