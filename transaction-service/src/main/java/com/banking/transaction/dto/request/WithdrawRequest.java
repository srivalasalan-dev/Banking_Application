package com.banking.transaction.dto.request;

import lombok.Data;

@Data
public class WithdrawRequest {

    private Long accountNumber;

    private Double amount;

}
