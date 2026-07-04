package com.xmajer.shopcore.orderservice.dto.response;

import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        Long price
) {
}