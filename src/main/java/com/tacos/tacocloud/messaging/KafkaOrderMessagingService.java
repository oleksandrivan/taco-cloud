package com.tacos.tacocloud.messaging;

import com.tacos.tacocloud.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

    @Value(value = "${kafka.orderTopic}")
    private String orderTopic;

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public KafkaOrderMessagingService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        log.info("Sending order {}", order);
        kafkaTemplate.send(orderTopic, order);
    }
}
