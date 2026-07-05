package com.xmajer.shopcore.orderservice.messaging.event;

import java.time.Instant;
import java.util.UUID;

public record PaymentCompletedEvent(
        UUID id,
        UUID orderId,
        UUID userID,
        Long totalAmount,
        boolean succeeded,
        Instant createdAt
) {
}
