package com.ecommerce.ecommerce_backend.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce_backend.entity.Order;
import com.ecommerce.ecommerce_backend.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public String placeOrder() {
        return orderService.placeOrder();
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }
}
