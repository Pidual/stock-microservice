package com.emazon.stock_microservice.infrastructure.input.rest;

import com.emazon.stock_microservice.application.dto.BrandDTO;
import com.emazon.stock_microservice.application.handler.IBrandHandler;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BrandRestController {

    private final IBrandHandler brandHandler;


    @Operation(summary = "Retrieve a paginated list of brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a page of brands", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping("/paged")
    public ResponseEntity<Page<BrandDTO>> getBrandsPaged(Pageable pageable) {
        Page<BrandDTO> brands = brandHandler.getAllBrandsPaged(pageable);
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }


    @Operation(summary = "Add a new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> addBrand(@RequestBody @Valid BrandDTO brandDTO){ //@Valid for validation before reaching the domain
        brandHandler.saveBrand(brandDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Retrieve a brand by its name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved the brand",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{brandName}")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable String brandName) {
        return new ResponseEntity<>(brandHandler.getBrand(brandName), HttpStatus.OK);
    }


    @Operation(summary = "Update an existing brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateBrand(@RequestBody @Valid BrandDTO brandDTO){
        brandHandler.updateBrand(brandDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete an existing brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteBrand(@RequestBody BrandDTO brandDTO){
        brandHandler.deleteBrand(brandDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Retrieve all brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all brands",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<BrandDTO>> getAllBrands(){
        return new ResponseEntity<>(brandHandler.findAllBrands(), HttpStatus.OK);
    }
}
