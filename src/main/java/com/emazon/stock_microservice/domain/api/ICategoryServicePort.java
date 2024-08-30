package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;

import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category); //POST

    Category getCategory(String categoryNAME); //GET

    void deleteCategoryById(Long categoryId); //DELETE

    void updateCategory(Category category); // PUT

    List<Category> getAllCategories();

    CustomPage<Category> getAllCategoriesPaged(CustomPageRequest customPageRequest); //GET

 }
