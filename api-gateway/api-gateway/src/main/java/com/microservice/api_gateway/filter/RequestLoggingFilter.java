package com.microservice.api_gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class RequestLoggingFilter implements GlobalFilter {

	private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		long startTime = System.currentTimeMillis();

		return chain.filter(exchange)

				.then(Mono.fromRunnable(() -> {

					long duration = System.currentTimeMillis() - startTime;

					log.info("{} {} took {} ms", exchange.getRequest().getMethod(),

							exchange.getRequest().getURI(),

							duration);
				}));
	}
}