package com.example.myproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CustomerRequest(
        @NotBlank(message = "Customer should be present")
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @NotNull(message="Customer email is required")
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Password should be present")
        @Size(min = 6, max = 12)
        @JsonProperty("password")
        String password,

        @NotBlank(message = "Address should be present")
        @JsonProperty("address")
        String address,

        @NotBlank(message = "City should be present")
        @JsonProperty("city")
        String city,

        @NotBlank(message = "Pincode should be present")
        @Size(min = 6, max = 6)
        @JsonProperty("pincode")
        String pincode
) {
}
