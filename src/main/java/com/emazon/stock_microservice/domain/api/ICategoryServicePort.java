package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


//what does this? why
public interface ICategoryServicePort {

    void saveCategory(Category category); //POST

    Category getCategoryById(Long categoryId); //GET

    void deleteCategoryById(Long categoryId); //DELETE

    void updateCategory(Category category); // PUT

     Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Page<Category> getAllCategoriesPaged(Pageable pageable); //GET

 }
