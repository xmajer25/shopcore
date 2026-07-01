package com.xmajer.shopcore.authservice.dto.response;

import com.xmajer.shopcore.authservice.data.enums.UserRole;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        UserRole userRole,
        Instant createdAt
) {
}
