package com.microservice.delivery_service.dto;

import java.time.LocalDateTime;

import com.microservice.delivery_service.entity.DeliveryStatus;

import lombok.Data;
@Data
public class DeliveryResponse {

    private Long id;

    private Long orderId;

    private String deliveryAgent;

    private DeliveryStatus status;

    private LocalDateTime createdAt;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(
//            Long id) {
//
//        this.id = id;
//    }
//
//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(
//            Long orderId) {
//
//        this.orderId = orderId;
//    }
//
//    public String getDeliveryAgent() {
//        return deliveryAgent;
//    }
//
//    public void setDeliveryAgent(
//            String deliveryAgent) {
//
//        this.deliveryAgent = deliveryAgent;
//    }
//
//    public DeliveryStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(
//            DeliveryStatus status) {
//
//        this.status = status;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(
//            LocalDateTime createdAt) {
//
//        this.createdAt = createdAt;
//    }
}