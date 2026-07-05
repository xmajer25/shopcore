package com.xmajer.shopcore.paymentservice.data.repository;

import com.xmajer.shopcore.paymentservice.data.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
