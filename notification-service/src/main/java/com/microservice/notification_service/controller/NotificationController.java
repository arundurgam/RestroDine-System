package com.microservice.notification_service.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.microservice.notification_service.dto.NotificationRequest;
import com.microservice.notification_service.dto.NotificationResponse;
import com.microservice.notification_service.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(
            NotificationService service) {

        this.service = service;
    }

    @PostMapping
    public NotificationResponse sendNotification(

            @Valid
            @RequestBody
            NotificationRequest request) {

        return service.sendNotification(
                request);
    
}
}