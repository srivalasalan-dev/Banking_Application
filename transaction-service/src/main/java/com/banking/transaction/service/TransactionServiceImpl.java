package com.banking.transaction.service;

import com.banking.transaction.client.AccountClient;
import com.banking.transaction.dto.request.*;
import com.banking.transaction.dto.response.TransactionResponse;
import com.banking.transaction.entity.Transaction;
import com.banking.transaction.repository.TransactionRepository;
import com.banking.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    private final AccountClient accountClient;

    @Override
    public TransactionResponse transfer(TransferRequest request) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Boolean> withdrawTask = () -> {

            WithdrawRequest withdraw = new WithdrawRequest();

            withdraw.setAccountNumber(request.getFromAccount());

            withdraw.setAmount(request.getAmount());

            accountClient.withdraw(withdraw);

            return true;

        };

        Callable<Boolean> depositTask = () -> {

            DepositRequest deposit = new DepositRequest();

            deposit.setAccountNumber(request.getToAccount());

            deposit.setAmount(request.getAmount());

            accountClient.deposit(deposit);

            return true;

        };

        try {

            Future<Boolean> withdrawFuture = executor.submit(withdrawTask);

            withdrawFuture.get();

            Future<Boolean> depositFuture = executor.submit(depositTask);

            depositFuture.get();

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            executor.shutdown();

        }

        Transaction transaction = Transaction.builder()
                .fromAccount(request.getFromAccount())
                .toAccount(request.getToAccount())
                .amount(request.getAmount())
                .type("TRANSFER")
                .status("SUCCESS")
                .transactionDate(LocalDateTime.now())
                .build();

        repository.save(transaction);

        return map(transaction);

    }

    @Override
    public List<TransactionResponse> history(Long accountNumber) {

        return repository
                .findByFromAccountOrToAccount(accountNumber, accountNumber)
                .stream()
                .map(this::map)
                .toList();

    }

    private TransactionResponse map(Transaction transaction) {

        return TransactionResponse.builder()
                .id(transaction.getId())
                .fromAccount(transaction.getFromAccount())
                .toAccount(transaction.getToAccount())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .status(transaction.getStatus())
                .transactionDate(transaction.getTransactionDate())
                .build();

    }

}
