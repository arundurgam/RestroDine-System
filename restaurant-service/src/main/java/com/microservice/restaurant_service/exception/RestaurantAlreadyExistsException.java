package com.microservice.restaurant_service.exception;

public class RestaurantAlreadyExistsException
        extends RuntimeException {

    public RestaurantAlreadyExistsException(
            String message) {

        super(message);
    }
}