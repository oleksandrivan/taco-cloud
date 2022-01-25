package com.tacos.tacocloud.controller;

import com.tacos.tacocloud.entity.TacoUser;
import javax.validation.Valid;

import com.tacos.tacocloud.entity.Order;
import com.tacos.tacocloud.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal
        TacoUser user) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error("Failed validation with a {}", error.getDefaultMessage()));
            return "orderForm";
        }
        order.setUser(user);
        log.info("Order submitted: " + order);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
