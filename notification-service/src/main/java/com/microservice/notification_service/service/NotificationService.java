package com.microservice.notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.microservice.notification_service.dto.NotificationRequest;
import com.microservice.notification_service.dto.NotificationResponse;
import com.microservice.notification_service.exception.NotificationException;

@Service
public class NotificationService {

    private static final Logger log =
            LoggerFactory.getLogger(
                    NotificationService.class);

    public NotificationResponse sendNotification(
            NotificationRequest request) {

        if (request == null) {

            throw new NotificationException(
                    "Notification Request Cannot Be Null");
        }

        log.info(
                "Sending Notification To : {}",
                request.getEmail());

        NotificationResponse response =
                new NotificationResponse();

        response.setEmail(
                request.getEmail());

        response.setStatus(
                "SUCCESS");

        response.setMessage(
                "Notification Sent Successfully");

        return response;
    }
}
