package com.emazon.stock_microservice.domain.spi;

import com.emazon.stock_microservice.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    Page<Brand> getAllBrands(Pageable pageable);

    Brand getBrand(Long id);

    void deleteBrand(Long id);

    void updateBrand(Brand brand);

    Brand getBrandByName(String name);

}
