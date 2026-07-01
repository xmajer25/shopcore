package com.xmajer.shopcore.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "This field must be a valid email")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {
}
