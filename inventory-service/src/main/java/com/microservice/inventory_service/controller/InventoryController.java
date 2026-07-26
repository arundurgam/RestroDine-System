package com.microservice.inventory_service.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.inventory_service.dto.InventoryRequest;
import com.microservice.inventory_service.dto.InventoryResponse;
import com.microservice.inventory_service.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	private static final Logger log =
			LoggerFactory.getLogger(
					InventoryController.class);

	private final InventoryService service;

	public InventoryController(
			InventoryService service) {

		this.service = service;
	}

	@PostMapping
	public ResponseEntity<InventoryResponse>
	addInventory(

			@Valid
			@RequestBody
			InventoryRequest request) {

		log.debug(
				"Received request to create inventory: {}",
				request.getItemName());

		InventoryResponse response =
				service.addInventory(request);

		return new ResponseEntity<>(
				response,
				HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<InventoryResponse>>
	getAllInventory() {

		return ResponseEntity.ok(
				service.getAllInventory());
	}

	@GetMapping("/{itemName}")
	public ResponseEntity<InventoryResponse>
	getInventory(

			@PathVariable
			String itemName) {

		log.debug(
				"Fetching inventory for item={}",
				itemName);

		InventoryResponse response =
				service.getInventory(itemName);

		return ResponseEntity.ok(
				response);
	}

	@PutMapping
	public ResponseEntity<String>
	reduceStock(

			@RequestParam String itemName,

			@RequestParam Integer quantity) {

		log.debug(
				"Reduce stock called for item={} qty={}",
				itemName,
				quantity);

		String result =
				service.reduceStock(
						itemName,
						quantity);

		return ResponseEntity.ok(
				result);
	}
	@PutMapping("/update")
	public InventoryResponse updateInventory(

	        @RequestParam String itemName,

	        @RequestParam Integer quantity) {

	    return service.updateInventory(
	            itemName,
	            quantity);
	}
}