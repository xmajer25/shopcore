package com.xmajer.shopcore.orderservice.data.model;

import com.xmajer.shopcore.orderservice.data.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shop_order")
@Getter
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, updatable = false)
    @Setter
    private UUID userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private OrderStatus orderStatus;

    @Column(nullable = false)
    @Setter
    private Long totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public Order(UUID userId, OrderStatus orderStatus){
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.totalAmount = 0L;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null){
            createdAt = Instant.now();
        }

        if (orderStatus == null) {
            orderStatus = OrderStatus.DRAFT;
        }
    }

    public void recalculateTotal(){
        this.totalAmount = items.stream()
                .mapToLong(OrderItem::getLineTotal)
                .sum();
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return id != null && id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
