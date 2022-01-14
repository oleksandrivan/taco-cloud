package com.tacos.tacocloud.repository;

import com.tacos.tacocloud.dto.Order;

public interface OrderRepository {
    
    Order save(Order order);

}
