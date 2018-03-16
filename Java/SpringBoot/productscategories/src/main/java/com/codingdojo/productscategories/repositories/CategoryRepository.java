package com.codingdojo.productscategories.repositories;

import com.codingdojo.productscategories.models.Category;
import com.codingdojo.productscategories.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    ArrayList<Category> findAll();
    ArrayList<Category> findByProductsNotContains(Product product);
}
