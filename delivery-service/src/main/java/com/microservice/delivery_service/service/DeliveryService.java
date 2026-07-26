package com.microservice.delivery_service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.microservice.delivery_service.dto.DeliveryRequest;
import com.microservice.delivery_service.dto.DeliveryResponse;
import com.microservice.delivery_service.entity.Delivery;
import com.microservice.delivery_service.entity.DeliveryStatus;
import com.microservice.delivery_service.exception.DeliveryNotFoundException;
import com.microservice.delivery_service.repository.DeliveryRepo;

@Service
public class DeliveryService {

    private static final Logger log =
            LoggerFactory.getLogger(
                    DeliveryService.class);

    private final DeliveryRepo repo;

    public DeliveryService(
            DeliveryRepo repo) {

        this.repo = repo;
    }

    public DeliveryResponse assignDelivery(
            DeliveryRequest request) {

        log.info(
                "Assigning Delivery For Order : {}",
                request.getOrderId());

        Delivery delivery =
                new Delivery();

        delivery.setOrderId(
                request.getOrderId());

        delivery.setDeliveryAgent(
                "Agent-Karthik");

        delivery.setStatus(
                DeliveryStatus.ASSIGNED);

        Delivery saved =
                repo.save(delivery);

        log.info(
                "Delivery Assigned Successfully : {}",
                saved.getId());

        return mapToResponse(
                saved);
    }

    @CacheEvict(
            value = "delivery",
            key = "#id")
    public DeliveryResponse updateStatus(
            Long id,
            DeliveryStatus status) {

        log.info(
                "Updating Delivery Status For Id : {}",
                id);

        Delivery delivery =
                repo.findById(id)
                        .orElseThrow(() ->
                                new DeliveryNotFoundException(
                                        "Delivery Not Found"));

        delivery.setStatus(
                status);

        Delivery updated =
                repo.save(delivery);

        log.info(
                "Delivery Status Updated : {} -> {}",
                id,
                status);

        return mapToResponse(
                updated);
    }

    @Cacheable(
            value = "delivery",
            key = "#id")
    public DeliveryResponse getDelivery(
            Long id) {

        log.info(
                "Fetching Delivery From Database : {}",
                id);

        Delivery delivery =
                repo.findById(id)
                        .orElseThrow(() ->
                                new DeliveryNotFoundException(
                                        "Delivery Not Found"));

        return mapToResponse(
                delivery);
    }

    public List<DeliveryResponse> getByStatus(
            DeliveryStatus status) {

        log.info(
                "Fetching Deliveries By Status : {}",
                status);

        return repo.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DeliveryResponse mapToResponse(
            Delivery delivery) {

        DeliveryResponse response =
                new DeliveryResponse();

        response.setId(
                delivery.getId());

        response.setOrderId(
                delivery.getOrderId());

        response.setDeliveryAgent(
                delivery.getDeliveryAgent());

        response.setStatus(
                delivery.getStatus());

        response.setCreatedAt(
                delivery.getCreatedAt());

        return response;
    }
    public List<DeliveryResponse>
    getAllDeliveries() {

        log.info(
                "Fetching All Deliveries");

        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}