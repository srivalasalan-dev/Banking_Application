package com.banking.transaction.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequest {

    @NotNull
    private Long fromAccount;

    @NotNull
    private Long toAccount;

    @NotNull
    private Double amount;

}
