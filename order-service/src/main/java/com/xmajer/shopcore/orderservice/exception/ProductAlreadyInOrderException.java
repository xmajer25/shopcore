package com.xmajer.shopcore.orderservice.exception;

import java.util.UUID;

public class ProductAlreadyInOrderException extends RuntimeException {
    public ProductAlreadyInOrderException(UUID orderId, UUID productId) {
        super("Product with id: '" + productId + "' is already included in order with id: '" + orderId + "'.");
    }
}
