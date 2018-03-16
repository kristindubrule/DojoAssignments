package com.codingdojo.productscategories.repositories;

import com.codingdojo.productscategories.models.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;
import com.codingdojo.productscategories.models.Category;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    ArrayList<Product> findAll();
    ArrayList<Product> findByCategoriesNotContains(Category category);
}
