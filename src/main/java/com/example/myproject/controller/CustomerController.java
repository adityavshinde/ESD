package com.example.myproject.controller;

import com.example.myproject.dto.CustomerRequest;
import com.example.myproject.dto.CustomerResponse;
import com.example.myproject.dto.LoginRequest;
import com.example.myproject.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> creating(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.creating(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(customerService.loginchecking(request));
    }
}