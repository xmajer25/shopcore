package com.xmajer.shopcore.orderservice.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "order_item")
@Getter
@Builder
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @Setter
    private Order order;

    @Column(nullable = false)
    @Setter
    private UUID productId;

    @Column(nullable = false)
    @Setter
    private String productNameSnapshot;

    @Column(nullable = false)
    @Setter
    private Long unitPriceSnapshot;

    @Column(nullable = false)
    @Setter
    private Integer quantity;

    @Column(nullable = false)
    @Setter
    private Long lineTotal;

    @PrePersist
    @PreUpdate
    protected void calculateLineTotal() {
        if (unitPriceSnapshot != null && quantity != null) {
            lineTotal = unitPriceSnapshot * quantity;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem item)) return false;
        return id != null && id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}