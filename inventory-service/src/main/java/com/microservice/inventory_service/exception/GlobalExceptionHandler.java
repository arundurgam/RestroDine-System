package com.microservice.inventory_service.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler
        extends ResponseEntityExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(
                    GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(

            MethodArgumentNotValidException ex,

            HttpHeaders headers,

            HttpStatusCode status,

            WebRequest request) {

        String errors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(
                                Collectors.joining(", "));

        ErrorResponse body =
                new ErrorResponse(
                        Instant.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation Failed",
                        errors,
                        request.getDescription(false));

        log.warn(
                "Validation Failed : {}",
                errors);

        return new ResponseEntity<>(
                body,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(
            InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleInventoryNotFound(

            InventoryNotFoundException ex,

            WebRequest request) {

        ErrorResponse body =
                new ErrorResponse(
                        Instant.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "Inventory Not Found",
                        ex.getMessage(),
                        request.getDescription(false));

        log.warn(
                "Inventory Not Found : {}",
                ex.getMessage());

        return new ResponseEntity<>(
                body,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(
            OutOfStockException.class)
    public ResponseEntity<ErrorResponse>
    handleOutOfStock(

            OutOfStockException ex,

            WebRequest request) {

        ErrorResponse body =
                new ErrorResponse(
                        Instant.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Out Of Stock",
                        ex.getMessage(),
                        request.getDescription(false));

        log.warn(
                "Out Of Stock : {}",
                ex.getMessage());

        return new ResponseEntity<>(
                body,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(
            Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGenericException(

            Exception ex,

            WebRequest request) {

        ErrorResponse body =
                new ErrorResponse(
                        Instant.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",
                        ex.getMessage(),
                        request.getDescription(false));

        log.error(
                "Unhandled Exception",
                ex);

        return new ResponseEntity<>(
                body,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}