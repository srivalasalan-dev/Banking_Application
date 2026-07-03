package com.banking.transaction.dto.request;

import lombok.Data;

@Data
public class DepositRequest {

    private Long accountNumber;

    private Double amount;

}
