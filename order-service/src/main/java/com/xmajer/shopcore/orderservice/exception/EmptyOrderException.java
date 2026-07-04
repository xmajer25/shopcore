package com.xmajer.shopcore.orderservice.exception;

import java.util.UUID;

public class EmptyOrderException extends RuntimeException {
    public EmptyOrderException(UUID id) {
        super("Order with id: '" + id + "' is empty and can not be closed.");
    }
}
