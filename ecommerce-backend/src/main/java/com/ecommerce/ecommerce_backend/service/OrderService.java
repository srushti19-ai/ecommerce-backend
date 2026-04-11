package com.ecommerce.ecommerce_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.entity.CartItem;
import com.ecommerce.ecommerce_backend.entity.Order;
import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.repository.CartItemRepository;
import com.ecommerce.ecommerce_backend.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // 🔥 PLACE ORDER (IMPORTANT)
//    public String placeOrder() {
//
//        List<CartItem> cartItems = cartItemRepository.findAll();
//
//        for (CartItem item : cartItems) {
//
//            Order order = new Order();
////            order.setUserId(item.getUserId());
////            order.setProductId(item.getProductId());
//            order.setUser(item.getCart().getUser());
//            order.setProduct(item.getProduct());
//            order.setQuantity(item.getQuantity());
//            order.setStatus("ORDERED");
//
//            orderRepository.save(order);
//        }
//
//        cartItemRepository.deleteAll(); // clear cart items after order
//
//        return "Order placed successfully";
//    }
    
    public String placeOrder() {

        List<CartItem> cartItems = cartItemRepository.findAll();

        double total = 0;

        for (CartItem item : cartItems) {

            Order order = new Order();

            // ✅ SET RELATIONS
            order.setUser(item.getCart().getUser());
            order.setProduct(item.getProduct());
            order.setQuantity(item.getQuantity());

            // ✅ PRICE CALCULATION
            double itemTotal = item.getProduct().getPrice() * item.getQuantity();
            total += itemTotal;

            order.setTotalPrice(itemTotal);

            order.setStatus("ORDERED"); // (keep this from your old code)

            orderRepository.save(order);
        }

        // ✅ CLEAR CART AFTER ORDER
        cartItemRepository.deleteAll();

        return "Order placed successfully. Total = " + total;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
