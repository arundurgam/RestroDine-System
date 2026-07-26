package com.microservice.auth_service.controller;

import org.springframework.web.bind.annotation.*;

import com.microservice.auth_service.dto.LoginRequest;
import com.microservice.auth_service.dto.LoginResponse;
import com.microservice.auth_service.dto.RegisterRequest;
import com.microservice.auth_service.entity.User;
import com.microservice.auth_service.security.JwtUtil;
import com.microservice.auth_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(
            @Valid
            @RequestBody RegisterRequest request) {

        return service.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid
            @RequestBody LoginRequest request) {

        return service.login(request);
    }

    @GetMapping("/validate")
    public String validate(
            @RequestParam String token) {

        return JwtUtil.extractUsername(token);
    }
}