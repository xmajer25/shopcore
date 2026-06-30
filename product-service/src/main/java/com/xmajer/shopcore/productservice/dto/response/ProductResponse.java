package com.xmajer.shopcore.productservice.dto.response;

import java.time.Instant;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        Long price,
        Instant createdAt
) {}
