package com.microservice.order_service.DTO;

import java.time.LocalDateTime;

import com.microservice.order_service.entity.OrderStatus;

import lombok.Data;
@Data
public class OrderResponse {

    private Long id;

    private String itemName;

    private Double price;

    private Long restaurantId;

    private OrderStatus status;

    private LocalDateTime createdAt;

 
}