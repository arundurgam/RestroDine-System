package com.microservice.restaurant_service.dto;

import java.time.LocalDateTime;

import com.microservice.restaurant_service.entity.RestaurantStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponse {

    private Long id;

    private String name;

    private String location;

    private String cuisineType;

    private RestaurantStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}