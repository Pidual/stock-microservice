package com.emazon.stock_microservice.infrastructure.input.rest;


import com.emazon.stock_microservice.application.dto.BrandDTO;
import com.emazon.stock_microservice.application.handler.IBrandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // get all (pageable)
    @Operation(summary = "Get some a page of the brands")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a page of the brands" )
    @GetMapping("/paged")
    public ResponseEntity<Page<BrandDTO>> getBrandsPaged(Pageable pageable) {
        Page<BrandDTO> brands = brandHandler.getAllBrandsPaged(pageable);
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    // Create POST
    @Operation(summary = "Adds a new brand")
    @ApiResponse(responseCode = "201", description = "Brand created successfully")
    @PostMapping("/")
    public ResponseEntity<Void> addBrand(@RequestBody BrandDTO brandDTO){
        brandHandler.saveBrand(brandDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{brandName}")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable String brandName) {
        return new ResponseEntity<>(brandHandler.getBrand(brandName), HttpStatus.OK);
    }

    // Update PUT/PATCH
    @PutMapping("/")
    public ResponseEntity<Void> updateBrand(@RequestBody BrandDTO brandDTO){
        brandHandler.updateBrand(brandDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete DELETE
    @DeleteMapping()
    public ResponseEntity<Void> deleteBrand(@RequestBody BrandDTO brandDTO){
        brandHandler.deleteBrand(brandDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get everything
    @Operation(summary = "Get all brands")
    @ApiResponse(responseCode = "200", description = "gets the whole rows of brands")
    @GetMapping("/")
    public ResponseEntity<List<BrandDTO>> getAllBrands(){
        return new ResponseEntity<>(brandHandler.findAllBrands(), HttpStatus.OK);
    }
}
