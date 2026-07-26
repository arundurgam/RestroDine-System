package com.microservice.inventory_service.event;

import lombok.Data;

@Data
public class OrderCreatedEvent {
	 private Long orderId;
	    private String itemName;
	    private int quantity;
}
