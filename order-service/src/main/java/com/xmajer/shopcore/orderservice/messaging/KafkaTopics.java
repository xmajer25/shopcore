package com.xmajer.shopcore.orderservice.messaging;

public final class KafkaTopics {

    public static final String ORDER_PAYMENT_REQUESTED = "order.payment.requested";
    public static final String PAYMENT_COMPLETED = "payment.completed";

    private KafkaTopics() {
    }
}