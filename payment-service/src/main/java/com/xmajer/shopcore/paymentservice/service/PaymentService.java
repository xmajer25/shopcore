package com.xmajer.shopcore.paymentservice.service;

import com.xmajer.shopcore.paymentservice.data.enums.PaymentStatus;
import com.xmajer.shopcore.paymentservice.data.model.Payment;
import com.xmajer.shopcore.paymentservice.data.repository.PaymentRepository;
import com.xmajer.shopcore.paymentservice.dto.response.PaymentResponse;
import com.xmajer.shopcore.paymentservice.exception.PaymentNotFoundException;
import com.xmajer.shopcore.paymentservice.exception.PaymentWasAlreadyPerformedException;
import com.xmajer.shopcore.paymentservice.mapper.PaymentMapper;
import com.xmajer.shopcore.paymentservice.messaging.event.OrderPaymentRequestEvent;
import com.xmajer.shopcore.paymentservice.messaging.event.PaymentCompletedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final ApplicationEventPublisher eventPublisher;

    private static final long MOCK_PAYMENT_LIMIT = 50_000L;

    public void processPaymentRequest(OrderPaymentRequestEvent event){
        Payment newPayment = new Payment();
        newPayment.setOrderId(event.orderId());
        newPayment.setUserId(event.userId());
        newPayment.setTotalAmount(event.amount());
        newPayment.setPaymentStatus(PaymentStatus.PENDING);

        paymentRepository.save(newPayment);
    }

    public PaymentResponse performPayment(UUID id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));

        if(!(payment.getPaymentStatus().equals(PaymentStatus.PENDING))){
            throw new PaymentWasAlreadyPerformedException(id);
        }

        payment.setPaymentStatus(
                payment.getTotalAmount() <= MOCK_PAYMENT_LIMIT
                ? PaymentStatus.SUCCEEDED
                : PaymentStatus.FAILED
        );

        Payment savedPayment = paymentRepository.save(payment);

        eventPublisher.publishEvent(toPaymentCompletedEvent(savedPayment));

        return paymentMapper.toResponse(savedPayment);
    }

    public List<PaymentResponse> findAll(){
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    private PaymentCompletedEvent toPaymentCompletedEvent(Payment payment) {
        return new PaymentCompletedEvent(
                payment.getOrderId(),
                payment.getUserId(),
                payment.getId(),
                payment.getTotalAmount(),
                payment.getPaymentStatus() == PaymentStatus.SUCCEEDED,
                payment.getCreatedAt()
        );
    }
}
