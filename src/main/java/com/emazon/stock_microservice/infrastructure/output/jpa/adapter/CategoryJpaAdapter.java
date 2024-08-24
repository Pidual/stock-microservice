package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_microservice.infrastructure.exceptions.category_expetions.CategoryAlreadyExistsException;
import com.emazon.stock_microservice.infrastructure.exceptions.category_expetions.CategoryNotFoundException;
import com.emazon.stock_microservice.infrastructure.exceptions.category_expetions.InvalidCategoryException;
import com.emazon.stock_microservice.infrastructure.exceptions.NoDataException;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
// This class communicates with the spi package that is in DOMAIN
// It has the implementations of how to use the database I guess
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;


    @Override
    public void saveCategory(Category category) {
        validateCategory(category);
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }


    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageable);

        if (categoryEntityPage.isEmpty()) {
            throw new NoDataException();
        }
        return categoryEntityPage.map(categoryEntityMapper::toCategory);
    }


    @Override
    public Category getCategory(Long categoryId) {
        return categoryEntityMapper.toCategory(categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new));
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryEntityMapper.toCategory(categoryRepository.findByName(categoryName).orElseThrow(CategoryNotFoundException::new));
    }

    @Override
    public void updateCategory(Category category) {
        validateCategory(category);
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }


    private void validateCategory(Category category) {
        // Check if the category has a description
        if (category.getDescription() == null || category.getDescription().trim().isEmpty()) {
            throw new InvalidCategoryException("Category description cannot be empty.");
        }
        // Ensure the name doesn't exceed 50 characters
        if (category.getName().length() > 50) {
            throw new InvalidCategoryException("Category name cannot exceed 50 characters.");
        }
        // Ensure the description doesn't exceed 90 characters
        if (category.getDescription().length() > 90) {
            throw new InvalidCategoryException("Category description cannot exceed 90 characters.");
        }
    }

}
