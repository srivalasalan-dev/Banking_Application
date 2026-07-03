package com.banking.transaction.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {

    private Long id;

    private Long fromAccount;

    private Long toAccount;

    private Double amount;

    private String type;

    private String status;

    private LocalDateTime transactionDate;

}
