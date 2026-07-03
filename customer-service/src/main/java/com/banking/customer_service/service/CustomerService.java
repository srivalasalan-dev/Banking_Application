package com.banking.customer_service.service;

import com.banking.customer_service.dto.request.UpdateCustomerRequest;
import com.banking.customer_service.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse getCustomerById(Long id);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse updateCustomer(Long id,
                                    UpdateCustomerRequest request);

    void deleteCustomer(Long id);

    CustomerResponse searchByEmail(String email);

}
