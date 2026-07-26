package com.microservice.delivery_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.delivery_service.entity.Delivery;
import com.microservice.delivery_service.entity.DeliveryStatus;

@Repository
public interface DeliveryRepo
        extends JpaRepository<Delivery, Long> {

    List<Delivery> findByStatus(
            DeliveryStatus status);
}