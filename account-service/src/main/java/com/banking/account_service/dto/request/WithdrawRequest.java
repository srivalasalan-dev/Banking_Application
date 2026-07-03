package com.banking.account_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class WithdrawRequest {

    @NotNull
    private Long accountNumber;

    @NotNull
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

}
