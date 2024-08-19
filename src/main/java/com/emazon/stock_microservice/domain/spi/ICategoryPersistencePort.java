package com.emazon.stock_microservice.domain.spi;

import com.emazon.stock_microservice.domain.model.Category;
import java.util.List;

// SPI??? Service provider interface
// Definición de la SPI: El desarrollador del framework o aplicación define una interfaz (o varias)
// que especifica métodos que deben ser implementados para proporcionar ciertas funcionalidades.

public interface ICategoryPersistencePort {

    void saveCategory(Category category); //POST

    List<Category> getAllCategories(); //GET

    Category getCategoryById(Long categoryId); //GET

    void deleteCategory(Long categoryId); //DELETE

    void updateCategory(Category category); // PATCH ?

    Category getCategoryByName(String categoryName);

}
