package com.microservice.api_gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.microservice.api_gateway.security.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter
        implements GlobalFilter {

    private static final Logger log =
            LoggerFactory.getLogger(
                    JwtAuthenticationFilter.class);

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        String path =
                exchange.getRequest()
                        .getURI()
                        .getPath();

        // Public APIs
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        String header =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("Authorization");

        if (header == null ||
                !header.startsWith("Bearer ")) {

            log.warn(
                    "Missing Authorization Header");

            return unauthorized(exchange);
        }

        String token =
                header.substring(7);

        if (!JwtUtil.validateToken(token)) {

            log.warn(
                    "Invalid JWT Token");

            return unauthorized(exchange);
        }

        try {

            String username =
                    JwtUtil.extractUsername(
                            token);

            String role =
                    JwtUtil.extractRole(
                            token);

            log.info(
                    "Authenticated User : {}",
                    username);

            log.info(
                    "Role : {}",
                    role);

            // ADMIN only
            if (path.startsWith("/restaurants")
                    && exchange.getRequest()
                    .getMethod()
                    .name()
                    .equals("POST")
                    && !role.equalsIgnoreCase("ADMIN")) {

                log.warn(
                        "Access Denied for User : {}",
                        username);

                return forbidden(exchange);
            }

            // CUSTOMER only
            if (path.startsWith("/orders")
                    && exchange.getRequest()
                    .getMethod()
                    .name()
                    .equals("POST")
                    && !role.equalsIgnoreCase("CUSTOMER")) {

                log.warn(
                        "Access Denied for User : {}",
                        username);

                return forbidden(exchange);
            }

            // DELIVERY_AGENT only
            if (path.startsWith("/deliveries")
                    && exchange.getRequest()
                    .getMethod()
                    .name()
                    .equals("PUT")
                    && !role.equalsIgnoreCase("DELIVERY_AGENT")) {

                log.warn(
                        "Access Denied for User : {}",
                        username);

                return forbidden(exchange);
            }

        } catch (Exception e) {

            log.error(
                    "JWT Validation Failed",
                    e);

            return unauthorized(exchange);
        }

        return chain.filter(exchange);
    }

    private Mono<Void> unauthorized(
            ServerWebExchange exchange) {

        exchange.getResponse()
                .setStatusCode(
                        HttpStatus.UNAUTHORIZED);

        return exchange.getResponse()
                .setComplete();
    }

    private Mono<Void> forbidden(
            ServerWebExchange exchange) {

        exchange.getResponse()
                .setStatusCode(
                        HttpStatus.FORBIDDEN);

        return exchange.getResponse()
                .setComplete();
    }
}