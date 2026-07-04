package com.xmajer.shopcore.orderservice.service;

import com.xmajer.shopcore.orderservice.client.ProductClient;
import com.xmajer.shopcore.orderservice.data.enums.OrderStatus;
import com.xmajer.shopcore.orderservice.data.model.Order;
import com.xmajer.shopcore.orderservice.data.model.OrderItem;
import com.xmajer.shopcore.orderservice.data.repository.OrderRepository;
import com.xmajer.shopcore.orderservice.dto.request.AddOrderItemRequest;
import com.xmajer.shopcore.orderservice.dto.response.OrderResponse;
import com.xmajer.shopcore.orderservice.dto.response.ProductResponse;
import com.xmajer.shopcore.orderservice.exception.EmptyOrderException;
import com.xmajer.shopcore.orderservice.exception.NoOpenedOrderException;
import com.xmajer.shopcore.orderservice.exception.ProductAlreadyInOrderException;
import com.xmajer.shopcore.orderservice.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductClient productClient;

    @Transactional
    public OrderResponse finishOrder(UUID userId){
        Order openedOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.DRAFT)
                .orElseThrow(() -> new NoOpenedOrderException(userId));

        if (openedOrder.getItems().isEmpty()) {
            throw new EmptyOrderException(openedOrder.getId());
        }

        openedOrder.setOrderStatus(OrderStatus.PAYMENT_PENDING);

        Order savedOrder = orderRepository.save(openedOrder);
        return orderMapper.toResponse(savedOrder);
    }

    @Transactional
    public OrderResponse addItem(UUID userId, AddOrderItemRequest request){
        Order order = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.DRAFT)
                .orElseGet(() -> new Order(userId, OrderStatus.DRAFT));

        ProductResponse productResponse = productClient.getProductById(request.productId());

        boolean productAlreadyInOrder = order.getItems().stream()
                .anyMatch(item -> item.getProductId().equals(productResponse.id()));

        if (productAlreadyInOrder) {
            throw new ProductAlreadyInOrderException(order.getId(), productResponse.id());
        }

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .productId(productResponse.id())
                .productNameSnapshot(productResponse.name())
                .unitPriceSnapshot(productResponse.price())
                .quantity(request.quantity())
                .lineTotal(request.quantity() * productResponse.price())
                .build();

        order.addItem(orderItem);
        order.recalculateTotal();

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }
}
