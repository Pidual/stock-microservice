package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBrandHandler {

    void saveBrand(BrandRequest brandRequest);

    void updateBrand(BrandRequest brandRequest);

    void deleteBrand(BrandRequest brandRequest);

    BrandRequest getBrandById(Long id);

    Page<BrandRequest> getAllBrandsPaged(Pageable pageable);

    List<BrandRequest> getAllBrands();

}
