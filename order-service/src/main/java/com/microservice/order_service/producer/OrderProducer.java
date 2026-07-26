package com.microservice.order_service.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.microservice.order_service.event.OrderCreatedEvent;

@Service
public class OrderProducer {

    private static final Logger log =
            LoggerFactory.getLogger(
                    OrderProducer.class);

    private final KafkaTemplate<String, Object>
            kafkaTemplate;

    public OrderProducer(
            KafkaTemplate<String, Object> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderCreated(
            OrderCreatedEvent event) {

        kafkaTemplate.send(
                "order-created",
                event);

        log.info(
                "Order Event Published : {}",
                event.getOrderId());
    }
}