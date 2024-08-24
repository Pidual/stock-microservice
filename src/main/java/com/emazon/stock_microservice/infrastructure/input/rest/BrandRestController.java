package com.emazon.stock_microservice.infrastructure.input.rest;


import com.emazon.stock_microservice.application.dto.BrandRequest;
import com.emazon.stock_microservice.application.dto.CategoryRequest;
import com.emazon.stock_microservice.application.handler.IBrandHandler;
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

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BrandRestController {

    private final IBrandHandler brandHandler;

    // Create POST
    @Operation(summary = "Adds a new brand")
    @ApiResponse(responseCode = "201", description = "Brand created succesfully")
    @PostMapping("/")
    public ResponseEntity<Void> addBrand(@RequestBody BrandRequest brandRequest){
        brandHandler.saveBrand(brandRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Read GET


    // get all (pageable)
    @Operation(summary = "Get some of the brands")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a page of the brands")
    @GetMapping("/")
    public ResponseEntity<Page<BrandRequest>> getBrands(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                                        @RequestParam(value = "sort", defaultValue = "name,asc") String sort){
        String[] sortParams = sort.split(",");
        String sortBy = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 ? Sort.Direction.fromString(sortParams[1]) : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return new ResponseEntity<>(brandHandler.getAllBrands(pageable), HttpStatus.OK);
    }

    // get BY ID

    @GetMapping("/{id}")
    public ResponseEntity<BrandRequest> getBrandById(@PathVariable Long id) {
        return new ResponseEntity<>(brandHandler.getBrandById(id), HttpStatus.OK);
    }





    // Update PUT/PATCH
    @PutMapping("/")
    public ResponseEntity<Void> updateBrand(@RequestBody BrandRequest brandRequest){
        brandHandler.updateBrand(brandRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Delete DELETE
    @DeleteMapping()
    public ResponseEntity<Void> deleteBrand(@RequestBody BrandRequest brandRequest){
        brandHandler.deleteBrand(brandRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
