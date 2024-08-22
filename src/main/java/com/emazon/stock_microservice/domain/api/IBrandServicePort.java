package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.application.dto.BrandRequest;
import com.emazon.stock_microservice.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {

    void saveBrand(Brand brand);

    List<Brand> getAllBrands();

    Brand getBrandById(Long id);

    void deleteBrandById(Long id);

    void updateBrand(Brand brand);



}
