package com.tacos.tacocloud.repository;

import com.tacos.tacocloud.entity.Order;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends ReactiveCrudRepository<Order, UUID> {

}
