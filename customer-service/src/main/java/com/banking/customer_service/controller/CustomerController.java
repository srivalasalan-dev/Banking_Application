package com.banking.customer_service.controller;

import com.banking.customer_service.dto.request.UpdateCustomerRequest;
import com.banking.customer_service.dto.response.CustomerResponse;
import com.banking.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id){

        return service.getCustomerById(id);

    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers(){

        return service.getAllCustomers();

    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request){

        return service.updateCustomer(id,request);

    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id){

        service.deleteCustomer(id);

        return "Customer Deleted Successfully";

    }

    @GetMapping("/search")
    public CustomerResponse searchCustomer(
            @RequestParam String email){

        return service.searchByEmail(email);

    }

}
