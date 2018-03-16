package com.codingdojo.productscategories.services;

import org.springframework.core.convert.converter.Converter;
import com.codingdojo.productscategories.models.Category;


public class CategoryIdToCategoryConverter implements Converter<String,Category> {
    private CategoryService categoryService;

    public CategoryIdToCategoryConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category convert(String id) {
        return categoryService.findCategoryById(Long.parseLong(id));
    }
}
