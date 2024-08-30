package com.emazon.stock_microservice.application.handler;


import com.emazon.stock_microservice.application.dto.CategoryDTO;
import com.emazon.stock_microservice.application.mapper.CategoryRequestMapper;
import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryHandlerTest {

    @Mock
    private ICategoryServicePort categoryUseCase;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllCategoriesPaged() {
        Pageable pageable = PageRequest.of(0, 2);
        Category category = new Category(1L, "Category1", "Description1");
        CategoryDTO categoryDTO = new CategoryDTO("Category1", "Description1");
        CustomPage<Category> customPage = new CustomPage<>(List.of(category), 1, 2, 0, true);
        when(categoryUseCase.getAllCategoriesPaged(any(CustomPageRequest.class))).thenReturn(customPage);
        when(categoryRequestMapper.toCategoryRequest(any(Category.class))).thenReturn(categoryDTO);
        Page<CategoryDTO> result = categoryHandler.getAllCategoriesPaged(pageable);
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getTotalElements());
        verify(categoryUseCase, times(1)).getAllCategoriesPaged(any(CustomPageRequest.class));
    }

    @Test
    void shouldSaveCategorySuccessfully() {
        CategoryDTO categoryDTO = new CategoryDTO("Category1", "Description1");
        Category category = new Category(1L, "Category1", "Description1");
        when(categoryRequestMapper.toCategory(any(CategoryDTO.class))).thenReturn(category);
        categoryHandler.saveCategory(categoryDTO);
        verify(categoryUseCase, times(1)).saveCategory(category);
    }

    @Test
    void shouldThrowExceptionWhenSavingCategoryWithEmptyName() {
        CategoryDTO categoryDTO = new CategoryDTO("", "Description1");
        Category category = new Category(1L, "", "Description1");
        when(categoryRequestMapper.toCategory(any(CategoryDTO.class))).thenReturn(category);
        doThrow(new IllegalArgumentException("Category name is empty")).when(categoryUseCase).saveCategory(category);
        assertThrows(IllegalArgumentException.class, () -> categoryHandler.saveCategory(categoryDTO));
        verify(categoryUseCase, times(1)).saveCategory(category);
    }

    @Test
    void shouldThrowExceptionWhenSavingCategoryWithEmptyDescription() {
        CategoryDTO categoryDTO = new CategoryDTO("Category1", "");
        Category category = new Category(1L, "Category1", "");
        when(categoryRequestMapper.toCategory(any(CategoryDTO.class))).thenReturn(category);
        doThrow(new IllegalArgumentException("Category description is empty")).when(categoryUseCase).saveCategory(category);
        assertThrows(IllegalArgumentException.class, () -> categoryHandler.saveCategory(categoryDTO));
        verify(categoryUseCase, times(1)).saveCategory(category);
    }

    @Test
    void shouldUpdateCategorySuccessfully() {
        CategoryDTO categoryDTO = new CategoryDTO("Category1", "New Description");
        Category existingCategory = new Category(1L, "Category1", "Old Description");
        when(categoryUseCase.getCategory(categoryDTO.getName())).thenReturn(existingCategory);
        categoryHandler.updateCategory(categoryDTO);
        assertEquals("New Description", existingCategory.getDescription());
        verify(categoryUseCase, times(1)).updateCategory(existingCategory);
    }

    @Test
    void shouldDeleteCategorySuccessfully() {
        categoryHandler.deleteCategory(1L);
        verify(categoryUseCase, times(1)).deleteCategoryById(1L);
    }

    @Test
    void shouldGetCategorySuccessfully() {
        Category category = new Category(1L, "Category1", "Description1");
        CategoryDTO categoryDTO = new CategoryDTO("Category1", "Description1");
        when(categoryUseCase.getCategory("Category1")).thenReturn(category);
        when(categoryRequestMapper.toCategoryRequest(category)).thenReturn(categoryDTO);
        CategoryDTO result = categoryHandler.getCategory("Category1");
        assertEquals(categoryDTO, result);
        verify(categoryUseCase, times(1)).getCategory("Category1");
    }

    @Test
    void shouldGetAllCategoriesSuccessfully() {
        Category category = new Category(1L, "Category1", "Description1");
        CategoryDTO categoryDTO = new CategoryDTO("Category1", "Description1");
        when(categoryUseCase.getAllCategories()).thenReturn(List.of(category));
        when(categoryRequestMapper.toCategoryRequest(category)).thenReturn(categoryDTO);
        List<CategoryDTO> result = categoryHandler.getAllCategories();
        assertEquals(1, result.size());
        verify(categoryUseCase, times(1)).getAllCategories();
    }











}