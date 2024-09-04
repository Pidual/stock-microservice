package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryJpaAdapter;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryJpaAdapter = categoryPersistencePort;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryJpaAdapter.getAllCategories();
    }

    @Override
    public CustomPage<Category> getAllCategoriesPaged(CustomPageRequest customPageRequest) {
        return categoryJpaAdapter.getCategoriesForPagination(customPageRequest);
    }

    @Override
    public Category getCategory(String categoryName) {
        return categoryJpaAdapter.getCategory(categoryName);
    }

    @Override
    public void saveCategory(Category category) {
        this.categoryJpaAdapter.saveCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryJpaAdapter.updateCategory(category);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryJpaAdapter.deleteCategory(categoryId);
    }


}
