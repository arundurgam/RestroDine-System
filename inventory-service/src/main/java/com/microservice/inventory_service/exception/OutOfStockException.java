package com.microservice.inventory_service.exception;

public class OutOfStockException
        extends RuntimeException {

    public OutOfStockException(
            String message) {

        super(message);
    }
}