package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
import com.emazon.stock_microservice.application.mapper.CategoryRequestMapper;
import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;


    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public Page<CategoryRequest> getAllCategories(Pageable pageable) {
        return categoryServicePort.getAllCategories(pageable)
                .map(categoryRequestMapper::toCategoryRequest);
    }

    @Override
    public CategoryRequest getCategory(Long id) {
        Category category = categoryServicePort.getCategoryById(id);
        return categoryRequestMapper.toCategoryRequest(category);
    }

    @Override
    public void updateCategory(CategoryRequest categoryDTO) {
        Category existingCategory = categoryServicePort.getCategoryByName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());
        categoryServicePort.updateCategory(existingCategory);
    }

    @Override
    public void deleteCategory(long id) {
        categoryServicePort.deleteCategoryById(id);
    }
}
