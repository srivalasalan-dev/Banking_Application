package com.banking.transaction.service;

import com.banking.transaction.client.AccountClient;
import com.banking.transaction.dto.request.*;
import com.banking.transaction.dto.response.TransactionResponse;
import com.banking.transaction.entity.Transaction;
import com.banking.transaction.exception.TransactionFailedException;
import com.banking.transaction.repository.TransactionRepository;
import com.banking.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    private final AccountClient accountClient;

    @Override
    public TransactionResponse transfer(TransferRequest request) {

      
        try {

            WithdrawRequest withdraw = new WithdrawRequest();

            withdraw.setAccountNumber(request.getFromAccount());

            withdraw.setAmount(request.getAmount());

            accountClient.withdraw(withdraw);

        } catch (Exception e) {

            throw new TransactionFailedException(
                    "Transfer failed while withdrawing from account "
                            + request.getFromAccount() + ": " + e.getMessage());

        }

      
        try {

            DepositRequest deposit = new DepositRequest();

            deposit.setAccountNumber(request.getToAccount());

            deposit.setAmount(request.getAmount());

            accountClient.deposit(deposit);

        } catch (Exception depositEx) {

            return handleDepositFailure(request, depositEx);

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

  
    private TransactionResponse handleDepositFailure(TransferRequest request, Exception depositEx) {

        try {

            DepositRequest refund = new DepositRequest();

            refund.setAccountNumber(request.getFromAccount());

            refund.setAmount(request.getAmount());

            accountClient.deposit(refund);

        } catch (Exception refundEx) {

            Transaction failed = Transaction.builder()
                    .fromAccount(request.getFromAccount())
                    .toAccount(request.getToAccount())
                    .amount(request.getAmount())
                    .type("TRANSFER")
                    .status("FAILED_UNRECOVERABLE")
                    .transactionDate(LocalDateTime.now())
                    .build();

            repository.save(failed);

            throw new TransactionFailedException(
                    "Transfer failed and the automatic refund also failed - manual reconciliation "
                            + "is required for account " + request.getFromAccount()
                            + ". Deposit error: " + depositEx.getMessage()
                            + " | Refund error: " + refundEx.getMessage());

        }

        Transaction failed = Transaction.builder()
                .fromAccount(request.getFromAccount())
                .toAccount(request.getToAccount())
                .amount(request.getAmount())
                .type("TRANSFER")
                .status("FAILED_REFUNDED")
                .transactionDate(LocalDateTime.now())
                .build();

        repository.save(failed);

        throw new TransactionFailedException(
                "Transfer failed while depositing to account " + request.getToAccount()
                        + " and has been refunded to account " + request.getFromAccount()
                        + ": " + depositEx.getMessage());

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
