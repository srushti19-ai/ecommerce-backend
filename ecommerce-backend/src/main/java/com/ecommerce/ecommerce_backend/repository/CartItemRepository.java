package com.ecommerce.ecommerce_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//    List<CartItem> findByCart(Cart cart);
    Optional<CartItem> findById(Long id);
    List<CartItem> findByCartUserId(Long userId);
}
