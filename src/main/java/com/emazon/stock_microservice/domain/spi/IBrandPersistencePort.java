package com.emazon.stock_microservice.domain.spi;

import com.emazon.stock_microservice.domain.model.Brand;

import java.util.List;

/**
 * Implemented in the INFRAESTRUCTURE
 * this si the JpaAdapter
 */
public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    Brand getBrand(String name);

    void deleteBrand(String name);

    void updateBrand(Brand brand);

    List<Brand> getAllBrands();

}
