package com.microservice.api_gateway.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
	private String message;
	private int status;
	private LocalDateTime timestamp;
}
