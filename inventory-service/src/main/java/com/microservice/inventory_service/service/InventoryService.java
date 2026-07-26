package com.microservice.inventory_service.service;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.microservice.inventory_service.dto.InventoryRequest;
import com.microservice.inventory_service.dto.InventoryResponse;
import com.microservice.inventory_service.entity.Inventory;
import com.microservice.inventory_service.exception.InventoryNotFoundException;
import com.microservice.inventory_service.exception.OutOfStockException;
import com.microservice.inventory_service.repository.InventoryRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class InventoryService {

	private static final Logger log =
			LoggerFactory.getLogger(
					InventoryService.class);

	private final InventoryRepo repo;

	public InventoryService(
			InventoryRepo repo) {

		this.repo = repo;
	}

	public InventoryResponse addInventory(
			InventoryRequest request) {

		Inventory entity =
				new Inventory();

		entity.setItemName(
				request.getItemName());

		entity.setQuantity(
				request.getQuantity());

		Inventory saved =
				repo.save(entity);

		log.info(
				"Created inventory item={} id={} qty={}",
				saved.getItemName(),
				saved.getId(),
				saved.getQuantity());

		return mapToResponse(
				saved);
	}

	public List<InventoryResponse>
	getAllInventory() {

		log.info(
				"Fetching all inventory items");

		return repo.findAll()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}

	@Cacheable(
			value = "inventory",
			key = "#itemName")
	@CircuitBreaker(
			name = "inventoryService",
			fallbackMethod = "inventoryFallback")
	@Retry(
			name = "inventoryService")
	public InventoryResponse getInventory(
			String itemName) {

		log.info(
				"Fetching Inventory From Database : {}",
				itemName);

		Inventory inventory =
				repo.findByItemName(itemName)
						.orElseThrow(() ->
								new InventoryNotFoundException(
										"Item Not Found: "
												+ itemName));

		return mapToResponse(
				inventory);
	}

	public InventoryResponse inventoryFallback(
			String itemName,
			Exception ex) {

		log.error(
				"Inventory Service Fallback triggered for item={} error={}",
				itemName,
				ex.getMessage());

		return new InventoryResponse(
				null,
				itemName,
				0);
	}

	@CacheEvict(
			value = "inventory",
			key = "#itemName")
	public String reduceStock(
			String itemName,
			Integer quantity) {

		Inventory inventory =
				repo.findByItemName(itemName)
						.orElseThrow(() ->
								new InventoryNotFoundException(
										"Item Not Found: "
												+ itemName));

		if (inventory.getQuantity()
				< quantity) {

			throw new OutOfStockException(
					"Requested quantity "
							+ quantity
							+ " exceeds available "
							+ inventory.getQuantity());
		}

		inventory.setQuantity(
				inventory.getQuantity()
						- quantity);

		repo.save(
				inventory);

		log.info(
				"Stock Reduced For Item : {} by {} at {}. Remaining={}",
				itemName,
				quantity,
				Instant.now(),
				inventory.getQuantity());

		return "Stock Updated Successfully";
	}

	private InventoryResponse mapToResponse(
			Inventory inventory) {

		return new InventoryResponse(
				inventory.getId(),
				inventory.getItemName(),
				inventory.getQuantity());
	}
	public InventoryResponse updateInventory(
	        String itemName,
	        Integer quantity) {

	    Inventory inventory =
	            repo.findByItemName(itemName)
	                    .orElseThrow(() ->
	                            new InventoryNotFoundException(
	                                    "Item Not Found"));

	    inventory.setQuantity(
	            quantity);

	    Inventory updated =
	            repo.save(inventory);

	    return mapToResponse(
	            updated);
	}
}