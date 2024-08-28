package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICategoryHandler {

    void saveCategory(CategoryDTO category); // CREATE

    CategoryDTO getCategory(Long id);  // GET

    void updateCategory(CategoryDTO categoryDTO); // UPDATE

    void deleteCategory(long id); // DELETE

    Page<CategoryDTO> getAllCategoriesPaged(Pageable pageable); // GET ALL

    List<CategoryDTO> getAllCategories();

}
