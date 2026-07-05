package com.xmajer.shopcore.orderservice.messaging.producer;

import com.xmajer.shopcore.orderservice.messaging.KafkaTopics;
import com.xmajer.shopcore.orderservice.messaging.event.OrderPaymentRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderPaymentRequestProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publish(OrderPaymentRequestEvent event) {
        kafkaTemplate
                .send(
                        KafkaTopics.ORDER_PAYMENT_REQUESTED,
                        event.orderId().toString(),
                        event
                )
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        log.error("Failed to publish OrderPaymentRequestedEvent for order {}", event.orderId(), exception);
                        return;
                    }

                    log.info(
                            "Published OrderPaymentRequestedEvent for order {} to topic {}",
                            event.orderId(),
                            KafkaTopics.ORDER_PAYMENT_REQUESTED
                    );
                });
    }
}