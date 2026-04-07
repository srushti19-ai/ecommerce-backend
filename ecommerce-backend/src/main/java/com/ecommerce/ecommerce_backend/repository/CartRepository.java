package com.ecommerce.ecommerce_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce_backend.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
