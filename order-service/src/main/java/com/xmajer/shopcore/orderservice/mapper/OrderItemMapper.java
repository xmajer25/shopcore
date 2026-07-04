package com.xmajer.shopcore.orderservice.mapper;

import com.xmajer.shopcore.orderservice.data.model.OrderItem;
import com.xmajer.shopcore.orderservice.dto.request.AddOrderItemRequest;
import com.xmajer.shopcore.orderservice.dto.response.OrderItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "productNameSnapshot", ignore = true)
    @Mapping(target = "unitPriceSnapshot", ignore = true)
    @Mapping(target = "lineTotal", ignore = true)
    OrderItem toEntity(AddOrderItemRequest request);

    OrderItemResponse toResponse(OrderItem orderItem);
}
