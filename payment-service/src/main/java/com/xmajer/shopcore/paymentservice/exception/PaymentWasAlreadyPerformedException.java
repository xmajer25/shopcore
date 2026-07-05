package com.xmajer.shopcore.paymentservice.exception;

import java.util.UUID;

public class PaymentWasAlreadyPerformedException extends RuntimeException {
    public PaymentWasAlreadyPerformedException(UUID id) {
        super("Payment with id: " + id + " was already handled by payment service");
    }
}
