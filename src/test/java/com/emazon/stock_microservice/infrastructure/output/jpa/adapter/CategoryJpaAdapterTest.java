package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.ICategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*; //this is for verify and times


class   CategoryJpaAdapterTest {

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


    //TODO: HACER TESTS DE INTEGRACION HUMBLE HUMBLE
    // THAT WASN'T THAT HUMBLE TO BEGIN WITH WTF!?
    @Test
    void saveCategory() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Duplicate description");

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Books");
        categoryEntity.setDescription("Reading materials");

        when(categoryRepository.findByName("Books")).thenReturn(categoryEntity);

        when(categoryEntityMapper.toEntity(any(Category.class))).thenReturn(categoryEntity);

        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        // Act
        categoryJpaAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(categoryEntity);
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