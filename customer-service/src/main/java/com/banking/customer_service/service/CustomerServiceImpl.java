package com.banking.customer_service.service;

import com.banking.customer_service.dto.request.UpdateCustomerRequest;
import com.banking.customer_service.dto.response.CustomerResponse;
import com.banking.customer_service.entity.Customer;
import com.banking.customer_service.exception.CustomerNotFoundException;
import com.banking.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerResponse getCustomerById(Long id){

        Customer customer=repository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));

        return map(customer);

    }

    @Override
    public List<CustomerResponse> getAllCustomers(){

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();

    }

    @Override
    public CustomerResponse updateCustomer(Long id,
                                           UpdateCustomerRequest request){

        Customer customer=repository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        repository.save(customer);

        return map(customer);

    }

    @Override
    public void deleteCustomer(Long id){

        Customer customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));

        repository.delete(customer);

    }

    @Override
    public CustomerResponse searchByEmail(String email){

        Customer customer=repository.findByEmail(email)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));

        return map(customer);

    }

    private CustomerResponse map(Customer customer){

        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();

    }

}
