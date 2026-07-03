package com.banking.auth_service.service;


import com.banking.auth_service.dto.request.LoginRequest;
import com.banking.auth_service.dto.request.RegisterRequest;
import com.banking.auth_service.dto.response.AuthResponse;
import com.banking.auth_service.entity.Customer;
import com.banking.auth_service.entity.Role;
import com.banking.auth_service.exception.CustomerAlreadyExistsException;
import com.banking.auth_service.repository.CustomerRepository;
import com.banking.auth_service.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new CustomerAlreadyExistsException("Email already exists.");
        }

        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new CustomerAlreadyExistsException("Phone number already exists.");
        }

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .createdAt(LocalDateTime.now())
                .build();

        customerRepository.save(customer);

        return AuthResponse.builder()
                .message("Customer registered successfully.")
                .token(null)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );

        Customer customer = customerRepository

                .findByEmail(request.getEmail())

                .orElseThrow();

        String token = jwtService.generateToken(customer);

        return AuthResponse.builder()

                .token(token)

                .message("Login Successful")

                .build();

    }

}
