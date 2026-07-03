package com.banking.transaction.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferRequest {

    @NotNull
    private Long fromAccount;

    @NotNull
    private Long toAccount;

    @NotNull
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

}
