package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBrandServicePort {

    void saveBrand(Brand brand);

    Brand getBrandById(Long id);

    void deleteBrandById(Long id);

    void updateBrand(Brand brand);

    Page<Brand> getAllBrandsPaged(Pageable pageable); // Later

    List<Brand> getAllBrands();


}
