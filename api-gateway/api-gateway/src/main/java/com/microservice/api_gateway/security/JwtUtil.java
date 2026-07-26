package com.microservice.api_gateway.security;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final String SECRET_KEY =
            "mySuperSecretJwtKeyForRestaurantManagementSystem2026";

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(
                    SECRET_KEY.getBytes());

    public static String extractUsername(
            String token) {

        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public static String extractRole(
            String token) {

        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(
                        "role",
                        String.class);
    }

    public static boolean validateToken(
            String token) {

        try {

            Jwts.parser()
                    .verifyWith(KEY)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}