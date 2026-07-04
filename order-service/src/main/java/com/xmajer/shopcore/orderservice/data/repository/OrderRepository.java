package com.xmajer.shopcore.orderservice.data.repository;

import com.xmajer.shopcore.orderservice.data.enums.OrderStatus;
import com.xmajer.shopcore.orderservice.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByUserIdAndOrderStatus(UUID userId, OrderStatus orderStatus);
    List<Order> findAllByUserId(UUID userId);
}
