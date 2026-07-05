package com.xmajer.shopcore.paymentservice.mapper;

import com.xmajer.shopcore.paymentservice.data.model.Payment;
import com.xmajer.shopcore.paymentservice.dto.response.PaymentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponse toResponse(Payment payment);
}
