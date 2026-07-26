package com.microservice.order_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.order_service.DTO.OrderRequest;
import com.microservice.order_service.DTO.OrderResponse;
import com.microservice.order_service.service.FoodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final FoodService service;

	public OrderController(
			FoodService service) {

		this.service = service;
	}

	@PostMapping
	public OrderResponse placeOrder(

			@Valid
			@RequestBody
			OrderRequest request) {

		return service.placeOrder(
				request);
	}

	@GetMapping
	public List<OrderResponse>
	getAllOrders() {

		return service.getAllOrders();
	}

	@GetMapping("/{id}")
	public OrderResponse getOrder(

			@PathVariable Long id) {

		return service.getOrder(id);
	}
}