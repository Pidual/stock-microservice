package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * This is the interface of brand handler
 * it's implemented in the application layer
 * it's called in the rest controller
 */
public interface IBrandHandler {

    Page<BrandDTO> getAllBrandsPaged(Pageable pageable);

    List<BrandDTO> findAllBrands();

    void saveBrand(BrandDTO brandDTO);

    void updateBrand(BrandDTO brandDTO);

    void deleteBrand(BrandDTO brandDTO);

    BrandDTO getBrand(String brandName);


}
