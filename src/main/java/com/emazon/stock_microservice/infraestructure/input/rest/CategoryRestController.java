package com.emazon.stock_microservice.infraestructure.input.rest;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
import com.emazon.stock_microservice.application.handler.ICategoryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Add a new category")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all categories")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of categories")
    @GetMapping("/")
    public ResponseEntity<List<CategoryRequest>> getAllCategories() {
        return new ResponseEntity<>(categoryHandler.getAllCategories(), HttpStatus.OK);
    }

    @Operation(summary = "Get a category by the id")
    @ApiResponse(responseCode = "200", description = "Successfully retried a category by its id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryRequest> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryHandler.getCategory(id), HttpStatus.OK);
    }



    @PutMapping("/")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.updateCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryHandler.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}