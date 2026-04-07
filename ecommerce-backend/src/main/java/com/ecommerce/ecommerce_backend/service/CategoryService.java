package com.ecommerce.ecommerce_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce_backend.entity.Category;
import com.ecommerce.ecommerce_backend.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
    private CategoryRepository categoryRepo;

    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
