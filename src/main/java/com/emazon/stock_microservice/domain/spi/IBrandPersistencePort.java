package com.emazon.stock_microservice.domain.spi;

import com.emazon.stock_microservice.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);



    Brand getBrand(Long id);

    void deleteBrand(Long id);

    void updateBrand(Brand brand);

    Brand getBrandByName(String name);

    Page<Brand> getAllBrandsPaged(Pageable pageable);

    List<Brand> getAllBrands();

}
