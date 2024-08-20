package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.spi.ICategoryPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public class CategoryUseCase implements ICategoryServicePort {

    //Dont use @Autowired bc domain driven desig
    private final ICategoryPersistencePort categoryPersistencePort; // esto

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) { // done
        this.categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryPersistencePort.getCategories(pageable);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryPersistencePort.getCategoryById(categoryId);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryPersistencePort.getCategoryByName(categoryName);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryPersistencePort.deleteCategory(categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        categoryPersistencePort.updateCategory(category);
    }
}
