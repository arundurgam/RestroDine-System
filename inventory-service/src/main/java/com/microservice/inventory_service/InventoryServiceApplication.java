package com.microservice.inventory_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Main application bootstrap for Inventory Service.
 * Enterprise improvements:
 * - @EnableCaching: enables Redis / Spring Cache abstraction for fast lookups.
 * - @EnableKafka: enables Kafka listener processing.
 * Using annotations keeps configuration declarative and test-friendly.
 */
@SpringBootApplication
@EnableKafka
@EnableCaching
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
