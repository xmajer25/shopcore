package com.xmajer.shopcore.orderservice.service;

import com.xmajer.shopcore.orderservice.data.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
}
