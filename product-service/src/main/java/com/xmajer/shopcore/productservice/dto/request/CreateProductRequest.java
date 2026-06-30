package com.xmajer.shopcore.productservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequest(
    @NotBlank(message = "Product name can not be empty")
    String name,

    @NotNull(message = "Product price can not be null")
    @Min(value = 1, message = "Product price must be greater than 0")
    Long price
){}
