package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.spi.ICategoryPersistencePort;

import java.util.List;


// HUH__?????
// ESTE ES NUESTRO PUERTO DE SALIDA
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
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
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
