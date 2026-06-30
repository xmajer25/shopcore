package com.xmajer.shopcore.productservice.mapper;

import com.xmajer.shopcore.productservice.data.model.Product;
import com.xmajer.shopcore.productservice.dto.request.CreateProductRequest;
import com.xmajer.shopcore.productservice.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring", imports = Instant.class)
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(CreateProductRequest request);

    ProductResponse toResponse(Product product);
}
