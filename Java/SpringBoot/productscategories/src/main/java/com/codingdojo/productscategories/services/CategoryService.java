package com.codingdojo.productscategories.services;

import com.codingdojo.productscategories.models.Category;
import com.codingdojo.productscategories.models.Product;
import com.codingdojo.productscategories.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public ArrayList<Category> allCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public ArrayList<Category> unusedCategories(Product product) {
        return categoryRepository.findByProductsNotContains(product);
    }

    public void addProduct(Category category, Product product) {
        category.getProducts().add(product);
        categoryRepository.save(category);
    }
}
