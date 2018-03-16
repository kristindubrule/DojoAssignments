package com.codingdojo.productscategories.controllers;

import com.codingdojo.productscategories.models.Product;
import com.codingdojo.productscategories.services.CategoryService;
import com.codingdojo.productscategories.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "new_product";
    }

    @PostMapping("new")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "new_product";
        } else {
            productService.addProduct(product);
            return "redirect:/product/"+product.getId();
        }
    }

    @RequestMapping("{id}")
    public String showProduct(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findProductById(id);
        if (product == null) {
            redirectAttributes.addFlashAttribute("errors","No product found");
            return "redirect:/product/new";
        } else {
            model.addAttribute("product",product);
            model.addAttribute("categories",categoryService.unusedCategories(product));
            return "show_product";
        }
    }

    @PostMapping("{productId}/category")
    public String addCategory(@PathVariable("productId") String productId, @RequestParam("categoryId") String categoryId) {
        System.out.println(categoryId);
        Product product = productService.findProductById(Long.parseLong(productId));
        productService.addCategory(product, categoryService.findCategoryById(Long.parseLong(categoryId)));
        return "redirect:/product/"+product.getId();
    }
}

