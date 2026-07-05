package com.xmajer.shopcore.orderservice.exception;

import java.util.UUID;

public class OrderAlreadyPaymentProcessedException extends RuntimeException{
    public OrderAlreadyPaymentProcessedException(UUID id){
        super("Order with id: " + id + " was already processed by payment-service");
    }
}
