package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.spi.ICategoryPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public class CategoryUseCase implements ICategoryServicePort {


    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        this.categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Page<Category> getAllCategoriesPaged(Pageable pageable) {
        return categoryPersistencePort.getAllCategoriesPaged(pageable);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }


    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryPersistencePort.getCategory(categoryId);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryPersistencePort.getCategoryByName(categoryName);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryPersistencePort.deleteCategory(categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        categoryPersistencePort.updateCategory(category);
    }
}
