package com.xmajer.shopcore.orderservice.data.repository;

import com.xmajer.shopcore.orderservice.data.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
