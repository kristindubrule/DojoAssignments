package com.codingdojo.productscategories.services;

import com.codingdojo.productscategories.models.Product;
import com.codingdojo.productscategories.models.Category;
import org.springframework.stereotype.Service;
import com.codingdojo.productscategories.repositories.ProductRepository;

import java.util.ArrayList;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addCategory(Product product, Category category) {
        product.getCategories().add(category);
        productRepository.save(product);
    }

    public ArrayList<Product> unusedProducts(Category category) {
        return productRepository.findByCategoriesNotContains(category);
    }
}
