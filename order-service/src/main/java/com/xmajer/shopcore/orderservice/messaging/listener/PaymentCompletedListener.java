package com.xmajer.shopcore.orderservice.messaging.listener;

import com.xmajer.shopcore.orderservice.messaging.KafkaTopics;
import com.xmajer.shopcore.orderservice.messaging.event.PaymentCompletedEvent;
import com.xmajer.shopcore.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentCompletedListener {

    private final OrderService orderService;

    @KafkaListener(
            topics = KafkaTopics.PAYMENT_COMPLETED,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listen(PaymentCompletedEvent event) {
        log.info(
                "Received PaymentCompletedEvent for order {} with status {}",
                event.orderId(),
                event.succeeded()
        );

        orderService.applyPaymentResult(event);
    }
}