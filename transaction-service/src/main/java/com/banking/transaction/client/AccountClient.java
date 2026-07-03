package com.banking.transaction.client;

import com.banking.transaction.dto.request.DepositRequest;
import com.banking.transaction.dto.request.WithdrawRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="ACCOUNT-SERVICE")
public interface AccountClient {

    @PostMapping("/accounts/deposit")
    Object deposit(@RequestBody DepositRequest request);

    @PostMapping("/accounts/withdraw")
    Object withdraw(@RequestBody WithdrawRequest request);

}
