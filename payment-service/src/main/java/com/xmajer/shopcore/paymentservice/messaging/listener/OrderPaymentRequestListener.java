package com.xmajer.shopcore.paymentservice.messaging.listener;

import com.xmajer.shopcore.paymentservice.messaging.KafkaTopics;
import com.xmajer.shopcore.paymentservice.messaging.event.OrderPaymentRequestEvent;
import com.xmajer.shopcore.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderPaymentRequestListener {

    private final PaymentService paymentService;

    @KafkaListener(
            topics = KafkaTopics.ORDER_PAYMENT_REQUESTED,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listen(OrderPaymentRequestEvent event) {
        log.info("Received OrderPaymentRequestedEvent for order {}", event.orderId());

        paymentService.processPaymentRequest(event);
    }
}