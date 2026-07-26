package com.microservice.api_gateway.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/restaurant")
    public String restaurantFallback() {

        return "Restaurant Service is temporarily unavailable";
    }

    @GetMapping("/order")
    public String orderFallback() {

        return "Order Service is temporarily unavailable";
    }

    @GetMapping("/payment")
    public String paymentFallback() {

        return "Payment Service is temporarily unavailable";
    }
}