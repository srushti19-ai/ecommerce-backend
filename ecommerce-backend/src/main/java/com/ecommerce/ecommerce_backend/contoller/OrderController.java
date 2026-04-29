package com.ecommerce.ecommerce_backend.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/user")
    public List<Order> getUserOrders(@RequestParam Long userId) {
        return orderService.getOrdersByUser(userId);
    }
    
//    @GetMapping("/user/{userId}")
//    public List<Order> getOrders(@PathVariable Long userId) {
//        return orderService.getUserOrders(userId);
//    }
}
