package com.microservice.order_service.exception;

public class RestaurantNotFoundException extends RuntimeException{
	public RestaurantNotFoundException(String message) {
		super(message);
	}

}
