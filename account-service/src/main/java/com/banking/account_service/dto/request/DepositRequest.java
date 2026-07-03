package com.banking.account_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepositRequest {

    @NotNull
    private Long accountNumber;

    @NotNull
    private Double amount;

}
