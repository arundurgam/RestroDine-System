package com.microservice.restaurant_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.restaurant_service.dto.RestaurantRequest;
import com.microservice.restaurant_service.dto.RestaurantResponse;
import com.microservice.restaurant_service.entity.Restaurant;
import com.microservice.restaurant_service.entity.RestaurantStatus;
import com.microservice.restaurant_service.exception.RestaurantAlreadyExistsException;
import com.microservice.restaurant_service.exception.RestaurantNotFoundException;
import com.microservice.restaurant_service.repo.RestaurantRepository;

@Service
public class RestaurantService {

	private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);

	private final RestaurantRepository repository;

	public RestaurantService(RestaurantRepository repository) {

		this.repository = repository;
	}

	@Transactional
	@Caching(evict = {

			@CacheEvict(value = "restaurants", allEntries = true),

			@CacheEvict(value = "restaurant", allEntries = true) })
	public RestaurantResponse saveRestaurant(RestaurantRequest request) {

		if (repository.findByName(request.getName()).isPresent()) {

			throw new RestaurantAlreadyExistsException("Restaurant already exists");
		}

		Restaurant restaurant = new Restaurant();

		restaurant.setName(request.getName());

		restaurant.setLocation(request.getLocation());

		restaurant.setCuisineType(request.getCuisineType());

		restaurant.setStatus(RestaurantStatus.ACTIVE);

		Restaurant saved = repository.save(restaurant);

		log.info("Restaurant created : {}", saved.getName());

		return mapToResponse(saved);
	}

	@Cacheable("restaurants")
	public List<RestaurantResponse> getAllRestaurants() {

		log.info("Fetching all restaurants");

		return repository.findAll().stream()

				.filter(r -> r.getStatus() == RestaurantStatus.ACTIVE)

				.map(this::mapToResponse)

				.collect(Collectors.toList());
	}

	@Cacheable(value = "restaurant", key = "#id")
	public RestaurantResponse getRestaurant(Long id) {

		Restaurant restaurant = repository.findById(id).orElseThrow(() -> {

			log.error("Restaurant not found : {}", id);

			return new RestaurantNotFoundException("Restaurant Not Found");
		});

		log.info("Restaurant fetched : {}", id);

		return mapToResponse(restaurant);
	}

	@Transactional
	@Caching(evict = {

			@CacheEvict(value = "restaurants", allEntries = true),

			@CacheEvict(value = "restaurant", key = "#id") })
	public RestaurantResponse updateRestaurant(Long id, RestaurantRequest request) {

		Restaurant restaurant = repository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurant Not Found"));

		restaurant.setName(request.getName());

		restaurant.setLocation(request.getLocation());

		restaurant.setCuisineType(request.getCuisineType());

		Restaurant updated = repository.save(restaurant);

		log.info("Restaurant updated : {}", updated.getId());

		return mapToResponse(updated);
	}

	@Transactional
	@Caching(evict = {

	        @CacheEvict(value = "restaurants", allEntries = true),

	        @CacheEvict(value = "restaurant", key = "#id")
	})
	public String deleteRestaurant(Long id) {

	    Restaurant restaurant =
	            repository.findById(id)
	                    .orElseThrow(() ->
	                            new RestaurantNotFoundException(
	                                    "Restaurant Not Found"));

	    // Soft Delete
	    restaurant.setStatus(
	            RestaurantStatus.INACTIVE);

	    repository.save(
	            restaurant);

	    log.info(
	            "Restaurant deactivated : {}",
	            id);

	    return "Restaurant Deleted Successfully";
	}

	private RestaurantResponse mapToResponse(Restaurant restaurant) {

		return RestaurantResponse.builder()

				.id(restaurant.getId())

				.name(restaurant.getName())

				.location(restaurant.getLocation())

				.cuisineType(restaurant.getCuisineType())

				.status(restaurant.getStatus())

				.createdAt(restaurant.getCreatedAt())

				.updatedAt(restaurant.getUpdatedAt())

				.build();
	}
}