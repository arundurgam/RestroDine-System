package com.microservice.order_service.event;

import lombok.Data;

@Data
public class OrderCreatedEvent {
	private Long orderId;
	private String itemName;
	private Integer quantity;
}
