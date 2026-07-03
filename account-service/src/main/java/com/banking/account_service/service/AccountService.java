package com.banking.account_service.service;

import com.banking.account_service.dto.request.CreateAccountRequest;
import com.banking.account_service.dto.request.DepositRequest;
import com.banking.account_service.dto.request.WithdrawRequest;
import com.banking.account_service.dto.response.AccountResponse;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest request);

    AccountResponse deposit(DepositRequest request);

    AccountResponse withdraw(WithdrawRequest request);

    Double getBalance(Long accountNumber);

    AccountResponse searchAccount(Long accountNumber);

}
