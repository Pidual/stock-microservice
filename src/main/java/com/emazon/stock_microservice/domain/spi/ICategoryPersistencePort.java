package com.emazon.stock_microservice.domain.spi;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;

import java.util.List;

public interface ICategoryPersistencePort {

    List<Category> getAllCategories();
    CustomPage<Category> getCategoriesForPagination(CustomPageRequest customPageRequest);

    Category getCategory(String categoryName);
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Long categoryId);




}
