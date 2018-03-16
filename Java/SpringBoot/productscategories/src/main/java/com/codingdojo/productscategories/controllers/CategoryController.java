package com.codingdojo.productscategories.controllers;

import com.codingdojo.productscategories.models.Category;
import com.codingdojo.productscategories.models.Product;
import com.codingdojo.productscategories.services.CategoryService;
import com.codingdojo.productscategories.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @RequestMapping("new")
    public String newCategory(@ModelAttribute("category")Category category) {
        return "new_category";
    }

    @PostMapping("new")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "new_category";
        } else {
            categoryService.addCategory(category);
            return "redirect:/category/"+category.getId();
        }
    }

    @RequestMapping("{id}")
    public String showCategory(@PathVariable(name="id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Category category = categoryService.findCategoryById(id);
        if (category == null) {
            redirectAttributes.addFlashAttribute("errors","No category found");
            return "redirect:/category/new";
        } else {
            model.addAttribute("category",category);
            model.addAttribute("products",productService.unusedProducts(category));
            return "show_category";
        }
    }

    @RequestMapping("{categoryId}/product")
    public String addProduct(@PathVariable("categoryId") Long categoryId, @RequestParam("productId") Long productId) {
        Category category = categoryService.findCategoryById(categoryId);
        Product product = productService.findProductById(productId);
        categoryService.addProduct(category,product);
        return "redirect:/category/"+category.getId();
    }
}
