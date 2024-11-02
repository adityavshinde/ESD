package com.example.myproject.service;

import com.example.myproject.dto.CustomerRequest;
import com.example.myproject.dto.CustomerResponse;
import com.example.myproject.entity.Customer;
import com.example.myproject.mapper.CustomerMapper;
import com.example.myproject.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        Customer cust = repo.save(customer);
        return mapper.toCustomerResponse(cust);
    }
}

