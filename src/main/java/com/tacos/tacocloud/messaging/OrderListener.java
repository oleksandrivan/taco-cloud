package com.tacos.tacocloud.messaging;

import com.tacos.tacocloud.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {

    @KafkaListener(topics = {"tacocloud.orders.topic"}, containerFactory = "orderConcurrentKafkaListenerContainerFactory")
    public void handle(Order order) {
        log.info("Order received {}", order);
    }

}
