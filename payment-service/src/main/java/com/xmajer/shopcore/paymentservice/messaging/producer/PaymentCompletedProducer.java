package com.xmajer.shopcore.paymentservice.messaging.producer;

import com.xmajer.shopcore.paymentservice.messaging.KafkaTopics;
import com.xmajer.shopcore.paymentservice.messaging.event.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentCompletedProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publish(PaymentCompletedEvent event) {
        kafkaTemplate
                .send(
                        KafkaTopics.PAYMENT_COMPLETED,
                        event.orderId().toString(),
                        event
                )
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        log.error("Failed to publish PaymentCompletedEvent for order {}", event.orderId(), exception);
                        return;
                    }

                    log.info(
                            "Published PaymentCompletedEvent for order {} with status {}",
                            event.orderId(),
                            event.succeeded()
                    );
                });
    }
}