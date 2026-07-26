package com.microservice.restaurant_service.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.microservice.restaurant_service.dto.RestaurantRequest;
import com.microservice.restaurant_service.dto.RestaurantResponse;
import com.microservice.restaurant_service.service.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurants")
@Validated
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(
            RestaurantService service) {

        this.service = service;
    }

    @PostMapping
    public RestaurantResponse createRestaurant(

            @Valid
            @RequestBody RestaurantRequest request) {

        return service.saveRestaurant(request);
    }

    @GetMapping
    public List<RestaurantResponse>
    getAllRestaurants() {

        return service.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantResponse getRestaurant(

            @PathVariable Long id) {

        return service.getRestaurant(id);
    }

    @PutMapping("/{id}")
    public RestaurantResponse updateRestaurant(

            @PathVariable Long id,

            @Valid
            @RequestBody RestaurantRequest request) {

        return service.updateRestaurant(
                id,
                request);
    }

    @DeleteMapping("/{id}")
    public String deleteRestaurant(

            @PathVariable Long id) {

        service.deleteRestaurant(id);

        return "Restaurant Deleted Successfully";
    }
    
}