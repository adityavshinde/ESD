package com.example.myproject.service;

import com.example.myproject.dto.CustomerRequest;
import com.example.myproject.dto.CustomerResponse;
import com.example.myproject.entity.Customer;
import com.example.myproject.exception.CustomerNotFound;
import com.example.myproject.helper.EncryptionService;
import com.example.myproject.helper.JwtHelper;
import com.example.myproject.mapper.CustomerMapper;
import com.example.myproject.repo.CustomerRepo;
import com.example.myproject.dto.LoginRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

        private final CustomerRepo repo;
        private final CustomerMapper mapper;
        private  final EncryptionService encryptionService;
        private final JwtHelper jwtHelper;

        public String creating(CustomerRequest request) {

            Customer customer = mapper.toEntity(request);
            customer.setPassword(encryptionService.encode(customer.getPassword()));

            repo.save(customer);
            return "created";

        }

        public Customer getCustomer(String email) {
            return repo.findByEmail(email)
                    .orElseThrow(() -> new CustomerNotFound(
                            format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                    ));
        }

        public CustomerResponse retrieveCustomer(String email, String token) {
            if(!headerChecking(token)) return null;
            Customer customer = getCustomer(email);
            return mapper.toCustomerResponse(customer);
        }

        public String loginchecking(LoginRequest  req){
            Customer customer=repo.findByEmail(req.email()).orElseThrow(() -> new CustomerNotFound("Not found"));
            if(encryptionService.validates(req.password(),customer.getPassword())){
                return jwtHelper.generateToken(req.email());
            }
            return "failed";


        }
        public boolean headerChecking(String recievedToken){
            String token = recievedToken.substring(7); // Extract token from "Bearer {token}"

            return jwtHelper.validateToken(token);
        }
}

