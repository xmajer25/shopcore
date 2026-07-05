package com.xmajer.shopcore.orderservice.config;

import com.xmajer.shopcore.orderservice.messaging.KafkaTopics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public KafkaAdmin.NewTopics shopcoreTopics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(KafkaTopics.ORDER_PAYMENT_REQUESTED)
                        .partitions(3)
                        .replicas(1)
                        .build(),

                TopicBuilder.name(KafkaTopics.PAYMENT_COMPLETED)
                        .partitions(3)
                        .replicas(1)
                        .build()
        );
    }
}