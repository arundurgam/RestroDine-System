package com.microservice.notification_service.dto;

import lombok.Data;

@Data
public class NotificationResponse {

    private String email;

    private String status;

    private String message;
}