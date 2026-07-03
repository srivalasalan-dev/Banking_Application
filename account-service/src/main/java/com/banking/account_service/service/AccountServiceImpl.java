package com.banking.account_service.service;

import com.banking.account_service.dto.request.CreateAccountRequest;
import com.banking.account_service.dto.request.DepositRequest;
import com.banking.account_service.dto.request.WithdrawRequest;
import com.banking.account_service.dto.response.AccountResponse;
import com.banking.account_service.entity.Account;
import com.banking.account_service.exception.AccountNotFoundException;
import com.banking.account_service.exception.InsufficientBalanceException;
import com.banking.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {

        Long accountNumber;

        do {
            accountNumber = 1000000000L + new Random().nextInt(900000000);
        } while (repository.existsByAccountNumber(accountNumber));

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .customerId(request.getCustomerId())
                .accountType(request.getAccountType())
                .balance(0.0)
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(account);

        return map(account);

    }

    @Override
    public AccountResponse deposit(DepositRequest request) {

        Account account = repository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        account.setBalance(account.getBalance() + request.getAmount());

        repository.save(account);

        return map(account);

    }

    @Override
    public AccountResponse withdraw(WithdrawRequest request) {

        Account account = repository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        if (account.getBalance() < request.getAmount()) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        account.setBalance(account.getBalance() - request.getAmount());

        repository.save(account);

        return map(account);

    }

    @Override
    public Double getBalance(Long accountNumber) {

        Account account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        return account.getBalance();

    }

    @Override
    public AccountResponse searchAccount(Long accountNumber) {

        Account account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        return map(account);

    }

    private AccountResponse map(Account account) {

        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();

    }

}
