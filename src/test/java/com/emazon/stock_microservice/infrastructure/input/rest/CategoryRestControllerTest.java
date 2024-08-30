package com.emazon.stock_microservice.infrastructure.input.rest;

import com.emazon.stock_microservice.application.dto.CategoryDTO;
import com.emazon.stock_microservice.application.handler.ICategoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*; //this is for verify and times

class CategoryRestControllerTest {

    @Mock
    private ICategoryHandler categoryHandler; //

    @InjectMocks
    private CategoryRestController categoryRestController; //


    // what is this?
    //
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //TODO do the after each

    @Test
    void addCategory_ShouldReturnCreatedStatus() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Electronics");
        categoryDTO.setDescription("Devices and gadgets");
        // Act
        ResponseEntity<Void> responseEntity = categoryRestController.addCategory(categoryDTO);
        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(categoryHandler, times(1)).saveCategory(categoryDTO);
    }


    @Test
    void getCategoryById() {
        // Arrange
        CategoryDTO expectedCategory = new CategoryDTO(); // creates an instance
        expectedCategory.setName("Electronics"); // sets the name
        expectedCategory.setDescription("Devices and gadgets");

        //This line uses Mockito to mock the categoryHandler object. Any call to the getCategory method with any long value will return the expectedCategory object.
        when(categoryHandler.getCategory(anyString())).thenReturn(expectedCategory);

        // Act Calls the actual controller method getCategoryById with the categoryId and stores the returned ResponseEntity in responseEntity.
        ResponseEntity<CategoryDTO> responseEntity = categoryRestController.getCategory(expectedCategory.getName());

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedCategory, responseEntity.getBody()); // Asserts that the body of the response is equal to the expectedCategory object.
        verify(categoryHandler, times(1)).getCategory(expectedCategory.getName()); //Verifies that the categoryHandler.getCategory method was called exactly once with the specified categoryId.
    }

    @Test
    void updateCategory() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Updated Category");
        categoryDTO.setDescription("Updated Description");

        doNothing().when(categoryHandler).updateCategory(any(CategoryDTO.class));

        // Act
        ResponseEntity<Void> responseEntity = categoryRestController.updateCategory(categoryDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(categoryHandler, times(1)).updateCategory(categoryDTO);
    }

    @Test
    void deleteCategory() {
        // Arrange
        Long categoryId = 1L;
        doNothing().when(categoryHandler).deleteCategory(anyLong());
        // Act
        ResponseEntity<Void> responseEntity = categoryRestController.deleteCategory(categoryId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(categoryHandler, times(1)).deleteCategory(categoryId);
    }
}