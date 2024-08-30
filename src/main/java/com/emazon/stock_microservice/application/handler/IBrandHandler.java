package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * This is the interface of brand handler
 * its implemented in the aplication layer
 * its called in the rest controller
 * and
 */
public interface IBrandHandler {

    void saveBrand(BrandDTO brandDTO);

    void updateBrand(BrandDTO brandDTO);

    void deleteBrand(BrandDTO brandDTO);

    BrandDTO getBrand(String brandName);

    Page<BrandDTO> getAllBrandsPaged(Pageable pageable);


    List<BrandDTO> findAllBrands();

}
