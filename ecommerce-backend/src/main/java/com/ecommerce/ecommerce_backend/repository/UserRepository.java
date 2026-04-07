package com.ecommerce.ecommerce_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	    User findByEmail(String email);
	    User findByResetToken(String token);
}
