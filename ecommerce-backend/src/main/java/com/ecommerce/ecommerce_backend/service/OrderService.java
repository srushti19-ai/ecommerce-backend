package com.ecommerce.ecommerce_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.entity.Order;
import com.ecommerce.ecommerce_backend.repository.CartRepository;
import com.ecommerce.ecommerce_backend.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    // 🔥 PLACE ORDER (IMPORTANT)
    public String placeOrder() {

        List<Cart> cartItems = cartRepository.findAll();

        for (Cart item : cartItems) {

            Order order = new Order();
            order.setUserId(item.getUserId());
            order.setProductId(item.getProductId());
            order.setQuantity(item.getQuantity());
            order.setStatus("ORDERED");

            orderRepository.save(order);
        }

        cartRepository.deleteAll(); // clear cart after order

        return "Order placed successfully";
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
