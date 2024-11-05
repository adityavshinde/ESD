package com.example.myproject.repo;

import com.example.myproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    public Customer findByEmail(String email);
}