package com.ecommerce.ecommerce_backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;

@Service
public class ProductService {
	 @Autowired
	    private ProductRepository productRepo;

	    public Product addProduct(Product product) {
	        return productRepo.save(product);
	    }

	    public List<Product> getAllProducts() {
	        return productRepo.findAll();
	    }

	    public Product getProductById(Long id) {
	        return productRepo.findById(id).orElse(null);
	    }

	    public Product updateProduct(Long id, Product product) {
	        Product existing = productRepo.findById(id).orElse(null);
	        if (existing != null) {
	            existing.setName(product.getName());
	            existing.setPrice(product.getPrice());
	            existing.setQuantity(product.getQuantity());
	            return productRepo.save(existing);
	        }
	        return null;
	    }

	    public void deleteProduct(Long id) {
	        productRepo.deleteById(id);
	    }
}
