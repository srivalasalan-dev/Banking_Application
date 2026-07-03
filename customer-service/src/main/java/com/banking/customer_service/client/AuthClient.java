package com.banking.customer_service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AUTH-SERVICE")
public interface AuthClient {

}
