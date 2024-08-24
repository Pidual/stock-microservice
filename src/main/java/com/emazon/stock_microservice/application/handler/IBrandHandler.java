package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandRequest;
import com.emazon.stock_microservice.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandHandler {

    void saveBrand(BrandRequest brandRequest);

    void updateBrand(BrandRequest brandRequest);

    void deleteBrand(BrandRequest brandRequest);


    Page<BrandRequest> getAllBrands(Pageable pageable);   //TODO: convertir a page

    BrandRequest getBrandById(Long id);

}
