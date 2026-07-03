package com.banking.transaction.controller;

import com.banking.transaction.dto.request.TransferRequest;
import com.banking.transaction.dto.response.TransactionResponse;
import com.banking.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/transfer")
    public TransactionResponse transfer(
            @Valid @RequestBody TransferRequest request){

        return service.transfer(request);

    }

    @GetMapping("/{accountNumber}")
    public List<TransactionResponse> history(
            @PathVariable Long accountNumber){

        return service.history(accountNumber);

    }

}
