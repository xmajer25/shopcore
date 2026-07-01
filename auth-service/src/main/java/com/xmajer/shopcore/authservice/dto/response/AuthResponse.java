package com.xmajer.shopcore.authservice.dto.response;

public record AuthResponse (
        String accessToken,
        String tokenType,
        Long expiresIn,
        UserResponse user
) {
}
