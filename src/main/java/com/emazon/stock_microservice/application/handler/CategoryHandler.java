package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
import com.emazon.stock_microservice.application.mapper.CategoryRequestMapper;
import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CategoryRequest> getAllCategories() {
        return categoryServicePort.getAllCategories()
                .stream()
                .map(categoryRequestMapper::toCategoryRequest)
                .collect(Collectors.toList());
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
        categoryServicePort.deleteCategory(id);
    }
}
