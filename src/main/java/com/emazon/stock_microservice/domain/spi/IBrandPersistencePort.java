package com.emazon.stock_microservice.domain.spi;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;

import java.util.List;

public interface IBrandPersistencePort {

    List<Brand> getAllBrands(); // retorna una lista
    CustomPage<Brand> getBrandsForPagination(CustomPageRequest customPageRequest); //retorna una paginacion

    Brand getBrand(String name);
    void saveBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrand(String name);




}
