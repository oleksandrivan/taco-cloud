package com.tacos.tacocloud.messaging;

import com.tacos.tacocloud.entity.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
