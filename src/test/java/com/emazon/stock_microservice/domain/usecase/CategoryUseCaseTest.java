package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    private Category validCategory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        validCategory = new Category(1L, "Ropa", "Category description");
    }

    @Test
    void getAllCategories_ShouldReturnCategories_WhenCategoriesExist() {
        // Arrange
        when(categoryPersistencePort.getAllCategories()).thenReturn(List.of(validCategory));

        // Act
        List<Category> result = categoryUseCase.getAllCategories();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(validCategory, result.get(0));
        verify(categoryPersistencePort, times(1)).getAllCategories();
    }

    @Test
    void getAllCategoriesPaged_ShouldReturnPagedCategories() {
        // Arrange
        CustomPageRequest pageRequest = new CustomPageRequest(0, 10, true, "name");
        CustomPage<Category> expectedPage = new CustomPage<>(List.of(validCategory), 1, 1, 0, true);
        when(categoryPersistencePort.getCategoriesForPagination(pageRequest)).thenReturn(expectedPage);

        // Act
        CustomPage<Category> result = categoryUseCase.getAllCategoriesPaged(pageRequest);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPage, result);
        verify(categoryPersistencePort, times(1)).getCategoriesForPagination(pageRequest);
    }

    @Test
    void getCategory_ShouldReturnCategory_WhenCategoryExists() {
        // Arrange
        when(categoryPersistencePort.getCategory(validCategory.getName())).thenReturn(validCategory);

        // Act
        Category result = categoryUseCase.getCategory(validCategory.getName());

        // Assert
        assertNotNull(result);
        assertEquals(validCategory, result);
        verify(categoryPersistencePort, times(1)).getCategory(validCategory.getName());
    }

    @Test
    void saveCategory_ShouldSaveCategory_WhenValid() {
        // Act
        categoryUseCase.saveCategory(validCategory);

        // Assert
        verify(categoryPersistencePort, times(1)).saveCategory(validCategory);
    }

    @Test
    void updateCategory_ShouldUpdateCategory_WhenValid() {
        // Act
        categoryUseCase.updateCategory(validCategory);

        // Assert
        verify(categoryPersistencePort, times(1)).updateCategory(validCategory);
    }

    @Test
    void deleteCategoryById_ShouldDeleteCategory_WhenCategoryExists() {
        // Act
        categoryUseCase.deleteCategoryById(validCategory.getId());

        // Assert
        verify(categoryPersistencePort, times(1)).deleteCategory(validCategory.getId());
    }
}
