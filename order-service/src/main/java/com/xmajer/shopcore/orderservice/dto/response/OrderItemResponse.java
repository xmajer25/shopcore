package com.xmajer.shopcore.orderservice.dto.response;

import java.util.UUID;

public record OrderItemResponse(
        UUID id,
        UUID productId,
        String productNameSnapshot,
        Long unitPriceSnapshot,
        Integer quantity,
        Long lineTotal
) {
}
