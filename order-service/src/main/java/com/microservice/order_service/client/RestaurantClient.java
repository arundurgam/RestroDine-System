package com.microservice.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.order_service.DTO.RestaurantDto;

@FeignClient(name = "RESTAURANT-SERVICE")
public interface RestaurantClient {

	@GetMapping("/restaurants/{id}")
	RestaurantDto getRestro(@PathVariable("id") Long id);
}