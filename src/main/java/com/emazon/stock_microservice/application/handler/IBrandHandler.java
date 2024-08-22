package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandRequest;
import com.emazon.stock_microservice.domain.model.Brand;

public interface IBrandHandler {

    void saveBrand(BrandRequest brandRequest);

    void updateBrand(BrandRequest brandRequest);

    void deleteBrandById(BrandRequest brandRequest);

    //List<Brand> getAllBrands(); TODO: convertir a page

    Brand getBrandById(Long id);

}
