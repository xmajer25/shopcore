package com.xmajer.shopcore.orderservice.dto.response;

import com.xmajer.shopcore.orderservice.data.enums.OrderStatus;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID userId,
        OrderStatus orderStatus,
        Long totalAmount,
        Instant createdAt,
        List<OrderItemResponse> items
) {
}
