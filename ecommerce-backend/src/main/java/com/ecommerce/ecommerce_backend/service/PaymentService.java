package com.ecommerce.ecommerce_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce_backend.entity.Order;
import com.ecommerce.ecommerce_backend.repository.OrderRepository;

@Service
public class PaymentService {
	@Autowired
    private OrderRepository orderRepository;

    public String makePayment(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // mock payment
        order.setPaymentStatus("PAID");
        order.setPaymentId("PAY" + System.currentTimeMillis());

        order.setStatus("COMPLETED"); 

        orderRepository.save(order);

        return "Payment Successful";
    }
}
