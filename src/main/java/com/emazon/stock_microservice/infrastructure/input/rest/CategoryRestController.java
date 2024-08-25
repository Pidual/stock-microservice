package com.emazon.stock_microservice.infrastructure.input.rest;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
import com.emazon.stock_microservice.application.handler.ICategoryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// documentation here http://localhost:8090/swagger-ui/index.html

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;


    //Create POST
    @Operation(summary = "Add a new category")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Get a category by the id")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a category by its id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryRequest> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryHandler.getCategory(id), HttpStatus.OK);
    }


    @Operation(summary = "Update a category by its name")
    @ApiResponse(responseCode = "204", description = "Successfully  updated a category using its name")
    @PutMapping("/")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.updateCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Deletes a category")
    @ApiResponse(responseCode = "204", description = "Successfully deleted a category")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryHandler.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Read GET
    @Operation(summary = "Get all categories")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a pageable of categories")
    @GetMapping("/paged")
    public ResponseEntity<Page<CategoryRequest>> getCategoriesPaged(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                                                    @RequestParam(value = "sort", defaultValue = "name,asc") String sort) {
        String[] sortParams = sort.split(",");
        String sortBy = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 ? Sort.Direction.fromString(sortParams[1]) : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return new ResponseEntity<>(categoryHandler.getAllCategoriesPaged(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Get every category")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the whole rows of categories")
    @GetMapping("/")
    public ResponseEntity<List<CategoryRequest>> getAllCategories() {
        return new ResponseEntity<>(categoryHandler.getAllCategories(), HttpStatus.OK);
    }

}