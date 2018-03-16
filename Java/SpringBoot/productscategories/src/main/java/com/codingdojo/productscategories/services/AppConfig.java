package com.codingdojo.productscategories.services;

import com.codingdojo.productscategories.models.Category;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableWebMvc
//@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
    private final CategoryService categoryService;

    public AppConfig(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CategoryIdToCategoryConverter(categoryService));
    }
}
