package com.xmajer.shopcore.paymentservice.dto.response;

import com.xmajer.shopcore.paymentservice.data.enums.PaymentStatus;

import java.time.Instant;
import java.util.UUID;

public record PaymentResponse(
        UUID id,
        UUID orderId,
        UUID userID,
        Long totalAmount,
        PaymentStatus paymentStatus,
        Instant createdAt
) {
}
