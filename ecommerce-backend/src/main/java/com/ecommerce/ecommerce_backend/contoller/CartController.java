package com.ecommerce.ecommerce_backend.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.entity.CartItem;
import com.ecommerce.ecommerce_backend.service.CartService;

import jakarta.persistence.OneToMany;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
    private CartService cartService;
	
//	@OneToMany(mappedBy = "cart")
//	private List<CartItem> items;
    
    @PostMapping("/add")
    public String addToCart(@RequestParam Long userId,
                           @RequestParam Long productId,
                           @RequestParam int quantity) {

        return cartService.addToCart(userId, productId, quantity);
    }

//    @GetMapping
//    public List<Cart> getCart() {
//        return cartService.getCartItems();
//    }
    
    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }

    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return "Item removed";
    }
    
    @PutMapping("/update")
    public String updateQuantity(@RequestParam Long cartItemId,
                                 @RequestParam int quantity) {
        return cartService.updateQuantity(cartItemId, quantity);
    }
}
