package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

 //what does this? why
public interface ICategoryServicePort {

    void saveCategory(Category category); //POST

    Page<Category> getAllCategories(Pageable pageable); //GET

    Category getCategoryById(Long categoryId); //GET

    void deleteCategory(Long categoryId); //DELETE

    void updateCategory(Category category); // PUT

     Category getCategoryByName(String name);
 }
