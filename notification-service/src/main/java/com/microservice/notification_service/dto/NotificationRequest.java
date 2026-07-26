package com.microservice.notification_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NotificationRequest {

    @Email(
            message = "Invalid Email")
    private String email;

    @NotBlank(
            message = "Message is required")
    private String message;
}