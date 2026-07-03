package com.banking.account_service.controller;

import com.banking.account_service.dto.request.*;
import com.banking.account_service.dto.response.AccountResponse;
import com.banking.account_service.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {

        return service.createAccount(request);

    }

    @PostMapping("/deposit")
    public AccountResponse deposit(@Valid @RequestBody DepositRequest request) {

        return service.deposit(request);

    }

    @PostMapping("/withdraw")
    public AccountResponse withdraw(@Valid @RequestBody WithdrawRequest request) {

        return service.withdraw(request);

    }

    @GetMapping("/balance/{accountNumber}")
    public Double getBalance(@PathVariable Long accountNumber) {

        return service.getBalance(accountNumber);

    }

    @GetMapping("/search/{accountNumber}")
    public AccountResponse searchAccount(@PathVariable Long accountNumber) {

        return service.searchAccount(accountNumber);

    }

}
