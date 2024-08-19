package com.emazon.stock_microservice.infraestructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.infraestructure.exception.CategoryAlreadyExistsException;
import com.emazon.stock_microservice.infraestructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_microservice.infraestructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_microservice.infraestructure.output.jpa.repository.ICategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; //this is for verify and times


class CategoryJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    void saveCategory() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Duplicate description");

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Books");
        categoryEntity.setDescription("Reading materials");

        when(categoryRepository.findByName("Books")).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(any(Category.class))).thenReturn(categoryEntity);
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        // Act
        categoryJpaAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Duplicate description");


        CategoryEntity existingCategoryEntity = new CategoryEntity();
        existingCategoryEntity.setName("Electronics");

        when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(existingCategoryEntity));

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryJpaAdapter.saveCategory(category));
    }


    @Test
    void getCategoryById() {
        // Arrange
        Long categoryId = 1L;
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        categoryEntity.setName("Electronics");
        categoryEntity.setDescription("Devices and gadgets");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(any(CategoryEntity.class)))
                .thenReturn(new Category(categoryId, "Electronics", "Devices and gadgets"));

        // Act
        Category category = categoryJpaAdapter.getCategoryById(categoryId);

        // Assert
        assertNotNull(category);
        assertEquals("Electronics", category.getName());
        assertEquals("Devices and gadgets", category.getDescription());
    }

    @Test
    void getCategoryByName() {
        // Arrange
        String categoryName = "Electronics";
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryName);
        categoryEntity.setDescription("Devices and gadgets");

        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(any(CategoryEntity.class)))
                .thenReturn(new Category(1L, categoryName, "Devices and gadgets"));

        // Act
        Optional<CategoryEntity> foundEntity = categoryRepository.findByName(categoryName);
        Category category = categoryJpaAdapter.getCategoryByName(categoryName);

        // Assert
        assertTrue(foundEntity.isPresent());
        assertEquals("Electronics", category.getName());
    }

    @Test
    void deleteCategory() {
        // Arrange
        Long categoryId = 1L;
        doNothing().when(categoryRepository).deleteById(categoryId);

        // Act
        categoryJpaAdapter.deleteCategory(categoryId);

        // Assert
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    void updateCategory() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Updated description");
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Electronics");
        categoryEntity.setDescription("Updated description");

        when(categoryEntityMapper.toEntity(any(Category.class))).thenReturn(categoryEntity);
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        // Act
        categoryJpaAdapter.updateCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(categoryEntity);
    }
}