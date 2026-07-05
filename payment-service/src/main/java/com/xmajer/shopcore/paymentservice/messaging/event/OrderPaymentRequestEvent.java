package com.xmajer.shopcore.paymentservice.messaging.event;

import java.util.UUID;

public record OrderPaymentRequestEvent(
        UUID orderId,
        UUID userId,
        Long amount
) {
}