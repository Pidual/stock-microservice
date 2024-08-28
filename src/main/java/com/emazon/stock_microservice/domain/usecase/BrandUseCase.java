package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import com.emazon.stock_microservice.domain.util.pageable.PageableUtil;

import java.util.List;

//  >>> ServicePort in the DOMAIN
public class BrandUseCase  implements IBrandServicePort {

    private final IBrandPersistencePort brandJpaAdapter; //

    // Persistence port
    public BrandUseCase(IBrandPersistencePort brandJpaAdapter) {
        this.brandJpaAdapter = brandJpaAdapter;
    }

    @Override
    public void saveBrand(Brand brand) {
        this.brandJpaAdapter.saveBrand(brand);
    }

    @Override
    public Brand getBrand(String name) {
        return brandJpaAdapter.getBrand(name);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandJpaAdapter.updateBrand(brand);
    }

    @Override
    public void deleteBrand(String name) {
        brandJpaAdapter.deleteBrand(name);
    }

    @Override
    public CustomPage<Brand> getAllBrandsPaged(CustomPageRequest customPageRequest) {
        List<Brand> allBrands = brandJpaAdapter.getAllBrands();
        return PageableUtil.paginateAndSort(allBrands, customPageRequest, Brand::getName);
    }


    @Override
    public List<Brand> getAllBrands() {
        return brandJpaAdapter.getAllBrands();
    }
}
