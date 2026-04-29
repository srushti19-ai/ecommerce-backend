package com.ecommerce.ecommerce_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce_backend.entity.Cart;
import com.ecommerce.ecommerce_backend.entity.CartItem;
import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.repository.CartItemRepository;
import com.ecommerce.ecommerce_backend.repository.CartRepository;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import com.ecommerce.ecommerce_backend.repository.UserRepository;
//
//@Service
//public class CartService {
//	@Autowired
//    private CartRepository cartRepository;
//
//    public Cart addToCart(Cart cart) {
//        return cartRepository.save(cart);
//    }
//
//    public List<Cart> getCartItems() {
//        return cartRepository.findAll();
//    }
//
//    public void removeItem(Long id) {
//        cartRepository.deleteById(id);
//    }
//}

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    public String addToCart(Long userId, Long productId, int quantity) {

        // 1. Get user
        User user = userRepo.findById(userId).orElseThrow();

        // 2. Get product
        Product product = productRepo.findById(productId).orElseThrow();

        // 3. Get or create cart
        Cart cart = cartRepo.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepo.save(cart);
        }

        // 4. Add item
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        cartItemRepo.save(item);

        return "Added to cart";
    }
    public List<Cart> getCartItems() {
        return cartRepo.findAll();
    }
    public List<CartItem> getCartByUser(Long userId) {
        return cartItemRepo.findByCartUserId(userId);
    }
//    public Cart getCartByUser(Long userId) {
//        User user = userRepo.findById(userId).orElseThrow();
//        return cartRepo.findByUser(user);
//    }

    public String updateQuantity(Long cartItemId, int quantity) {

        CartItem item = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        //Update quantity
        item.setQuantity(quantity);

        cartItemRepo.save(item);

        return "Quantity updated successfully";
    }
    
    public void removeItem(Long id) {

        CartItem item = cartItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cartItemRepo.delete(item);
    }
}