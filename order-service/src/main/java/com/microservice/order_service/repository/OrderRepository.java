package com.microservice.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.order_service.entity.FoodOrder;

@Repository
public interface OrderRepository extends JpaRepository<FoodOrder, Long> {

}
