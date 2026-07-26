package com.microservice.delivery_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.delivery_service.entity.AgentStatus;
import com.microservice.delivery_service.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {

	Optional<DeliveryAgent> findFirstByStatus(AgentStatus status);
}