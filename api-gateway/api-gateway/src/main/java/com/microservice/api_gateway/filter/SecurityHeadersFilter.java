package com.microservice.api_gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class SecurityHeadersFilter
        implements GlobalFilter {

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        exchange.getResponse()
                .getHeaders()
                .add(
                        "X-Content-Type-Options",
                        "nosniff");

        exchange.getResponse()
                .getHeaders()
                .add(
                        "X-Frame-Options",
                        "DENY");

        return chain.filter(exchange);
    }
}