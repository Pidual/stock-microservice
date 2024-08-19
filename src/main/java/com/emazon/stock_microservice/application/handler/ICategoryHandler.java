package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.CategoryRequest;


import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryRequest category);

    List<CategoryRequest> getAllCategories();

    CategoryRequest getCategory(Long id);

    void updateCategory(CategoryRequest categoryDTO);

    void deleteCategory(long id);
}
