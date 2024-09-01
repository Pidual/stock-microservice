package com.emazon.stock_microservice.infrastructure.input.rest;

import com.emazon.stock_microservice.application.dto.CategoryDTO;
import com.emazon.stock_microservice.application.handler.ICategoryHandler;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;


import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    // Read GET
    @Operation(summary = "Get all categories")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a pageable of categories")
    @GetMapping("/paged")
    public ResponseEntity<Page<CategoryDTO>> getCategoriesPaged(Pageable pageable){
        Page<CategoryDTO> categories = categoryHandler.getAllCategoriesPaged(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    @Operation(summary = "Get every category")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the whole rows of categories")
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryHandler.getAllCategories(), HttpStatus.OK);
    }

    //Create POST
    @Operation(summary = "Add a new category")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryHandler.saveCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Get a category by the name")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a category by its id")
    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String categoryName) {
        return new ResponseEntity<>(categoryHandler.getCategory(categoryName), HttpStatus.OK);
    }


    @Operation(summary = "Update a category by its name")
    @ApiResponse(responseCode = "204", description = "Successfully  updated a category using its name")
    @PutMapping("/")
    public ResponseEntity<Void> updateCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryHandler.updateCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Deletes a category")
    @ApiResponse(responseCode = "204", description = "Successfully deleted a category")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryHandler.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}