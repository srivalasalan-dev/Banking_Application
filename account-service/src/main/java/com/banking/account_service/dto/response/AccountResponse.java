package com.banking.account_service.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {

    private Long accountNumber;

    private Long customerId;

    private String accountType;

    private Double balance;

    private String status;

}
