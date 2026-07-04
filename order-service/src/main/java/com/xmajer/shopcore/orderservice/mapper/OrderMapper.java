package com.xmajer.shopcore.orderservice.mapper;

import com.xmajer.shopcore.orderservice.data.model.Order;
import com.xmajer.shopcore.orderservice.dto.response.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toResponse(Order order);
}
