package com.ecommerce.ecommerce_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce_backend.entity.Order;
import com.ecommerce.ecommerce_backend.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
//	List<Order> findByUserId(Long userId);
	List<Order> findByUser(User user);
}
