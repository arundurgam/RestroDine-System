package com.microservice.delivery_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.delivery_service.dto.DeliveryRequest;
import com.microservice.delivery_service.dto.DeliveryResponse;
import com.microservice.delivery_service.entity.DeliveryStatus;
import com.microservice.delivery_service.service.DeliveryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

	private final DeliveryService service;

	public DeliveryController(DeliveryService service) {

		this.service = service;
	}

	@PostMapping
	public DeliveryResponse assignDelivery(

			@Valid @RequestBody DeliveryRequest request) {

		return service.assignDelivery(request);
	}

	@PutMapping("/{id}")
	public DeliveryResponse updateStatus(

			@PathVariable Long id,

			@RequestParam DeliveryStatus status) {

		return service.updateStatus(id, status);
	}

	@GetMapping("/{id}")
	public DeliveryResponse getDelivery(@PathVariable Long id) {

		return service.getDelivery(id);
	}

	@GetMapping("/status/{status}")
	public List<DeliveryResponse> getByStatus(

			@PathVariable DeliveryStatus status) {

		return service.getByStatus(status);
	}
	@GetMapping
	public List<DeliveryResponse>
	getAllDeliveries() {

	    return service.getAllDeliveries();
	}
}