package com.microservice.inventory_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.inventory_service.entity.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
	Optional<Inventory> findByItemName(String itemName);

}
