package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICategoryHandler {

    void saveCategory(CategoryRequest category); // CREATE

    CategoryRequest getCategory(Long id);  // GET

    void updateCategory(CategoryRequest categoryDTO); // UPDATE

    void deleteCategory(long id); // DELETE

    Page<CategoryRequest> getAllCategoriesPaged(Pageable pageable); // GET ALL

    List<CategoryRequest> getAllCategories();

}
