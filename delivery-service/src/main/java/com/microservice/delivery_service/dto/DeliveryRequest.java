package com.microservice.delivery_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class DeliveryRequest {

    @NotNull(
            message = "Order Id is required")
    private Long orderId;

//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(
//            Long orderId) {
//
//        this.orderId = orderId;
//    }
}