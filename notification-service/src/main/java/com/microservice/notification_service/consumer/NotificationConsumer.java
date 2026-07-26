package com.microservice.notification_service.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.microservice.notification_service.dto.NotificationRequest;
import com.microservice.notification_service.event.OrderCreatedEvent;
import com.microservice.notification_service.service.NotificationService;

@Component
public class NotificationConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(
                    NotificationConsumer.class);

    private final NotificationService
            notificationService;

    public NotificationConsumer(
            NotificationService notificationService) {

        this.notificationService =
                notificationService;
    }

    @KafkaListener(
            topics = "order-created",
            groupId = "notification-group")
    public void consume(
            OrderCreatedEvent event) {

        log.info(
                "Order Event Received : {}",
                event.getOrderId());

        try {

            NotificationRequest request =
                    new NotificationRequest();

            request.setEmail(
                    "customer@gmail.com");

            request.setMessage(
                    "Your Order "
                            + event.getOrderId()
                            + " has been placed successfully");

            notificationService
                    .sendNotification(
                            request);

            log.info(
                    "Notification Sent For Order : {}",
                    event.getOrderId());

        } catch (Exception ex) {

            log.error(
                    "Failed To Process Order Event : {}",
                    event.getOrderId(),
                    ex);
        }

        log.info(
                "Item : {} Quantity : {}",
                event.getItemName(),
                event.getQuantity());
    }
}