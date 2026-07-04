package com.xmajer.shopcore.orderservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddOrderItemRequest(
        @NotNull(message = "Product id is required")
        UUID productId,

        @NotNull(message = "Quantity of product is required")
        @Min(value = 1, message = "Quantity of product must be greater than 0")
        Integer quantity
) {
}
