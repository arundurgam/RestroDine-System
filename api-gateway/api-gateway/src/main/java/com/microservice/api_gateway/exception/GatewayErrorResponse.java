package com.microservice.api_gateway.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GatewayErrorResponse {

	private String message;

	private int status;

	private LocalDateTime timestamp;

//    public GatewayErrorResponse(
//            String message,
//            int status,
//            LocalDateTime timestamp) {
//
//        this.message = message;
//        this.status = status;
//        this.timestamp = timestamp;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
}