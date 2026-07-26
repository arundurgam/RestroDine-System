package com.microservice.auth_service.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.auth_service.dto.LoginRequest;
import com.microservice.auth_service.dto.LoginResponse;
import com.microservice.auth_service.dto.RegisterRequest;
import com.microservice.auth_service.entity.User;
import com.microservice.auth_service.repository.UserRepository;
import com.microservice.auth_service.security.JwtUtil;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder pwd;

    public UserService(UserRepository repo,
                       BCryptPasswordEncoder pwd) {

        this.repo = repo;
        this.pwd = pwd;
    }

    public User register(RegisterRequest request) {

        if (repo.findByUsername(
                request.getUsername()).isPresent()) {

            throw new RuntimeException(
                    "Username already exists");
        }

        User user = new User();

        user.setUsername(
                request.getUsername());

        user.setPassword(
                pwd.encode(
                        request.getPassword()));

        user.setRole(
                request.getRole());

        return repo.save(user);
    }

    public LoginResponse login(
            LoginRequest request) {

        User user = repo.findByUsername(
                request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User Not Found"));

        if (!pwd.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        String token =
                JwtUtil.generateToken(
                        user.getUsername(),
                        user.getRole());

        return new LoginResponse(
                token,
                user.getRole().name());
    }

    public User findByUsername(
            String username) {

        return repo.findByUsername(
                username)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));
    }
}