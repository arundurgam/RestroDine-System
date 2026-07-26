package com.microservice.order_service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.microservice.order_service.DTO.OrderRequest;
import com.microservice.order_service.DTO.OrderResponse;
import com.microservice.order_service.DTO.RestaurantDto;
import com.microservice.order_service.client.RestaurantClient;
import com.microservice.order_service.entity.FoodOrder;
import com.microservice.order_service.entity.OrderStatus;
import com.microservice.order_service.event.OrderCreatedEvent;
import com.microservice.order_service.exception.RestaurantNotFoundException;
import com.microservice.order_service.producer.OrderProducer;
import com.microservice.order_service.repository.OrderRepository;

import feign.FeignException;

@Service
public class FoodService {

	private static final Logger log =
			LoggerFactory.getLogger(
					FoodService.class);

	private final OrderRepository repo;

	private final RestaurantClient client;

	private final OrderProducer orderProducer;

	public FoodService(
			OrderRepository repo,
			RestaurantClient client,
			OrderProducer orderProducer) {

		this.repo = repo;
		this.client = client;
		this.orderProducer = orderProducer;
	}

	public OrderResponse placeOrder(
			OrderRequest request) {

		try {

			RestaurantDto restaurant =
					client.getRestro(
							request.getRestaurantId());

			log.info(
					"Restaurant Found : {}",
					restaurant.getName());

		} catch (FeignException ex) {

			throw new RestaurantNotFoundException(
					"Restaurant Not Found");
		}

		FoodOrder order =
				new FoodOrder();

		order.setItemName(
				request.getItemName());

		order.setPrice(
				request.getPrice());

		order.setRestaurantId(
				request.getRestaurantId());

		order.setStatus(
				OrderStatus.CREATED);

		FoodOrder savedOrder =
				repo.save(order);

		OrderCreatedEvent event =
				new OrderCreatedEvent();

		event.setOrderId(
				savedOrder.getId());

		event.setItemName(
				savedOrder.getItemName());

		event.setQuantity(
				1);

		orderProducer.publishOrderCreated(
				event);

		log.info(
				"Order Created : {}",
				savedOrder.getId());

		return mapToResponse(
				savedOrder);
	}

	public OrderResponse getOrder(
			Long id) {

		FoodOrder order =
				repo.findById(id)
						.orElseThrow(() ->
								new RuntimeException(
										"Order Not Found"));

		return mapToResponse(
				order);
	}

	public List<OrderResponse> getAllOrders() {

		return repo.findAll()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}

	private OrderResponse mapToResponse(
			FoodOrder order) {

		OrderResponse response =
				new OrderResponse();

		response.setId(
				order.getId());

		response.setItemName(
				order.getItemName());

		response.setPrice(
				order.getPrice());

		response.setRestaurantId(
				order.getRestaurantId());

		response.setStatus(
				order.getStatus());

		response.setCreatedAt(
				order.getCreatedAt());

		return response;
	}
}