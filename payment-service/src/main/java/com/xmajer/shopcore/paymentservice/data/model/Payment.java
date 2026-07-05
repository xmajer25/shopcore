package com.xmajer.shopcore.paymentservice.data.model;


import com.xmajer.shopcore.paymentservice.data.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Table
@Entity(name = "payment")
@Getter
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false, updatable = false)
    @Setter
    UUID orderId;

    @Column(nullable = false, updatable = false)
    @Setter
    UUID userId;

    @Column(nullable = false)
    @Setter
    private Long totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    PaymentStatus paymentStatus;

    @Column(nullable = false, updatable = false)
    Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if(createdAt == null){
            createdAt = Instant.now();
        }

        if(paymentStatus == null){
            paymentStatus = PaymentStatus.PENDING;
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Payment payment)) return false;
        return id != null && id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
