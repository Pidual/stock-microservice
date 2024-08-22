package com.emazon.stock_microservice.infrastructure.input.rest;


import com.emazon.stock_microservice.application.dto.BrandRequest;
import com.emazon.stock_microservice.application.dto.CategoryRequest;
import com.emazon.stock_microservice.application.handler.CategoryHandler;
import com.emazon.stock_microservice.application.handler.IBrandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BrandRestController {

    private final IBrandHandler brandHandler;


    public ResponseEntity<Void> addBrand(@RequestBody BrandRequest brandRequest){
        brandHandler.saveBrand(brandRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<Void> updateBrand(@RequestBody BrandRequest brandRequest){
        brandHandler.updateBrand(brandRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
