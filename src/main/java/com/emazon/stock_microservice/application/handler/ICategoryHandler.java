package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICategoryHandler {

    void saveCategory(CategoryRequest category); // CREATE

    Page<CategoryRequest> getAllCategories(Pageable pageable); // GET ALL

    CategoryRequest getCategory(Long id);  // GET

    void updateCategory(CategoryRequest categoryDTO); // UPDATE

    void deleteCategory(long id); // DELETE
}
