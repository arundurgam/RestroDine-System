package com.microservice.delivery_service.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.microservice.delivery_service.entity.AgentStatus;
import com.microservice.delivery_service.entity.Delivery;
import com.microservice.delivery_service.entity.DeliveryAgent;
import com.microservice.delivery_service.entity.DeliveryStatus;
import com.microservice.delivery_service.event.OrderCreatedEvent;
import com.microservice.delivery_service.repository.DeliveryAgentRepository;
import com.microservice.delivery_service.repository.DeliveryRepo;

@Component
public class DeliveryConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(
                    DeliveryConsumer.class);

    private final DeliveryRepo repo;

    private final DeliveryAgentRepository
            agentRepository;

    public DeliveryConsumer(
            DeliveryRepo repo,
            DeliveryAgentRepository agentRepository) {

        this.repo = repo;
        this.agentRepository =
                agentRepository;
    }

    @KafkaListener(
            topics = "order-created",
            groupId = "delivery-group")
    public void consumeOrderCreated(
            OrderCreatedEvent event) {

        log.info(
                "Order Event Received : {}",
                event.getOrderId());

        DeliveryAgent agent =
                agentRepository
                        .findFirstByStatus(
                                AgentStatus.AVAILABLE)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "No Delivery Agent Available"));

        Delivery delivery =
                new Delivery();

        delivery.setOrderId(
                event.getOrderId());

        delivery.setDeliveryAgent(
                agent.getName());

        delivery.setStatus(
                DeliveryStatus.ASSIGNED);

        repo.save(delivery);

        agent.setStatus(
                AgentStatus.BUSY);

        agentRepository.save(
                agent);

        log.info(
                "Order {} Assigned To Agent {}",
                event.getOrderId(),
                agent.getName());
    }
}