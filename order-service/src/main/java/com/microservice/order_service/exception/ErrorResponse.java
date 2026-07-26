package com.microservice.order_service.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
	private String message;
	private int status;
	private LocalDateTime timestamp;

	public ErrorResponse(String message, int status, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}

}
