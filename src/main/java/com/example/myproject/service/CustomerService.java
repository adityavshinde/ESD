package com.example.myproject.service;

import com.example.myproject.dto.CustomerRequest;
import com.example.myproject.dto.CustomerResponse;
import com.example.myproject.entity.Customer;
import com.example.myproject.mapper.CustomerMapper;
import com.example.myproject.repo.CustomerRepo;
import com.example.myproject.dto.LoginRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    public String creating(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "created";
    }

    public String loginchecking(LoginRequest request) {
        Customer customer = repo.findByEmail(request.email());
        if (customer == null) {
            return "Invalid email";
        }
        else{
            if(customer.getPassword().equals(request.password())){
                return "Login successful";
            }
            else return "Invalid password";
        }
    }
}

