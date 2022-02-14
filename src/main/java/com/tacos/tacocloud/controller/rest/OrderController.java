package com.tacos.tacocloud.controller.rest;

import com.tacos.tacocloud.entity.Order;
import com.tacos.tacocloud.messaging.OrderMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController("RestOrderController")
@RequestMapping(path = "api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderMessagingService orderMessagingService;

    @Autowired
    public OrderController(OrderMessagingService orderMessagingService) {
        this.orderMessagingService = orderMessagingService;
    }

    @PostMapping(path = "/dummy")
    public void createDummyOrder() {
        Order dummy = new Order();
        dummy.setId(1L);
        dummy.setName("Olik");
        dummy.setStreet("Fake st.");
        dummy.setCity("Springfield");
        dummy.setState("MD");
        dummy.setZip("28901");
        dummy.setCcNumber("4485410741422437");
        dummy.setCcExpiration("12/12");
        dummy.setCcCVV("123");
        dummy.setPlacedAt(new Date());
        orderMessagingService.sendOrder(dummy);
    }

}
