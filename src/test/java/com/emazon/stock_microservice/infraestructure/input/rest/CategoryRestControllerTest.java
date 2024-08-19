package com.emazon.stock_microservice.infraestructure.input.rest;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
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
    private ICategoryHandler categoryHandler;

    @InjectMocks
    private CategoryRestController categoryRestController;


    // what is this?
    //
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCategory_ShouldReturnCreatedStatus() {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("Devices and gadgets");
        // Act
        ResponseEntity<Void> responseEntity = categoryRestController.addCategory(categoryRequest);
        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(categoryHandler, times(1)).saveCategory(categoryRequest);
    }

    @Test
    void getAllCategories_ShouldReturnOkStatus() {
        // Act
        ResponseEntity<?> responseEntity = categoryRestController.getAllCategories();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getCategoryById() {
        // Arrange
        Long categoryId = 1L; // initializes with the value 1 and as a long
        CategoryRequest expectedCategory = new CategoryRequest(); // creates an instance
        expectedCategory.setName("Electronics"); // sets the name
        expectedCategory.setDescription("Devices and gadgets");

        //This line uses Mockito to mock the categoryHandler object. Any call to the getCategory method with any long value will return the expectedCategory object.
        when(categoryHandler.getCategory(anyLong())).thenReturn(expectedCategory);

        // Act Calls the actual controller method getCategoryById with the categoryId and stores the returned ResponseEntity in responseEntity.
        ResponseEntity<CategoryRequest> responseEntity = categoryRestController.getCategoryById(categoryId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedCategory, responseEntity.getBody()); // Asserts that the body of the response is equal to the expectedCategory object.
        verify(categoryHandler, times(1)).getCategory(categoryId); //Verifies that the categoryHandler.getCategory method was called exactly once with the specified categoryId.
    }

    @Test
    void updateCategory() {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Updated Category");
        categoryRequest.setDescription("Updated Description");

        doNothing().when(categoryHandler).updateCategory(any(CategoryRequest.class));

        // Act
        ResponseEntity<Void> responseEntity = categoryRestController.updateCategory(categoryRequest);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(categoryHandler, times(1)).updateCategory(categoryRequest);
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