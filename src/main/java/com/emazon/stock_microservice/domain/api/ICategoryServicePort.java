package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.domain.model.Category;

import java.util.List;

 //idk why
public interface ICategoryServicePort {

    void saveCategory(Category category); //POST

    List<Category> getAllCategories(); //GET

    Category getCategoryById(Long categoryId); //GET

    void deleteCategory(Long categoryId); //DELETE

    void updateCategory(Category category); // PUT

     Category getCategoryByName(String name);
 }
