package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryRequest category);

    Page<CategoryRequest> getAllCategories(Pageable pageable);


    CategoryRequest getCategory(Long id);

    void updateCategory(CategoryRequest categoryDTO);

    void deleteCategory(long id);
}
