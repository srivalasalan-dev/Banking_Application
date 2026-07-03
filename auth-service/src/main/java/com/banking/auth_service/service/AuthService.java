package com.banking.auth_service.service;


import com.banking.auth_service.dto.request.LoginRequest;
import com.banking.auth_service.dto.request.RegisterRequest;
import com.banking.auth_service.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}
