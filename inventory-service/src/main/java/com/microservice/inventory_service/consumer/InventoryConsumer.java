package com.microservice.inventory_service.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microservice.inventory_service.entity.Inventory;
import com.microservice.inventory_service.event.OrderCreatedEvent;
import com.microservice.inventory_service.exception.InventoryNotFoundException;
import com.microservice.inventory_service.exception.OutOfStockException;
import com.microservice.inventory_service.repository.InventoryRepo;

/**
 * Kafka consumer that listens for OrderCreatedEvent.
 * Improvements:
 * - Uses SLF4J for structured logs instead of System.out.
 * - Validates existence and availability before reducing stock.
 * - Throws domain-specific exceptions which are handled centrally.
 * - Evicts cache after updates to keep cache consistent.
 */
@Service
public class InventoryConsumer {

    private static final Logger log = LoggerFactory.getLogger(InventoryConsumer.class);

    private final InventoryRepo repo;

    public InventoryConsumer(InventoryRepo repo) {
        this.repo = repo;
    }

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    @CacheEvict(value = "inventory", key = "#event.itemName")
    public void consume(OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent: orderId={} item={} qty={}", event.getOrderId(), event.getItemName(), event.getQuantity());

        Inventory inventory = repo.findByItemName(event.getItemName())
                .orElseThrow(() -> new InventoryNotFoundException("Item Not Found: " + event.getItemName()));

        if (inventory.getQuantity() < event.getQuantity()) {
            log.warn("Insufficient stock for item={} requested={} available={}", event.getItemName(), event.getQuantity(), inventory.getQuantity());
            throw new OutOfStockException("Not enough stock for item: " + event.getItemName());
        }

        inventory.setQuantity(inventory.getQuantity() - event.getQuantity());
        repo.save(inventory);

        log.info("Inventory Updated for item={} remaining={}", inventory.getItemName(), inventory.getQuantity());
    }
}
