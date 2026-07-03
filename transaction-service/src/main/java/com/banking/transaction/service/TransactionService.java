package com.banking.transaction.service;

import com.banking.transaction.dto.request.TransferRequest;
import com.banking.transaction.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    TransactionResponse transfer(TransferRequest request);

    List<TransactionResponse> history(Long accountNumber);

}
