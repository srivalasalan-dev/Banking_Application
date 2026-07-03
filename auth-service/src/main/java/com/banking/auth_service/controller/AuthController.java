package com.banking.auth_service.controller;

import com.banking.auth_service.dto.request.LoginRequest;
import com.banking.auth_service.dto.request.RegisterRequest;
import com.banking.auth_service.dto.response.AuthResponse;
import com.banking.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @Valid @RequestBody RegisterRequest request){

        return authService.register(request);

    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request){

        return authService.login(request);

    }

}

