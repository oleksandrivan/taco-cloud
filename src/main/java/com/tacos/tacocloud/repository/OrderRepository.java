package com.tacos.tacocloud.repository;

import com.tacos.tacocloud.entity.Order;

public interface OrderRepository {
    
    Order save(Order order);

}
