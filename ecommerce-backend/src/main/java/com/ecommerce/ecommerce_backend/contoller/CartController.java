package com.ecommerce.ecommerce_backend.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
    private CartService cartService;

    @PostMapping
    public Cart addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    @GetMapping
    public List<Cart> getCart() {
        return cartService.getCartItems();
    }

    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return "Item removed";
    }
}
