package com.banking.account_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAccountRequest {

    @NotNull
    private Long customerId;

    private String accountType;

}
