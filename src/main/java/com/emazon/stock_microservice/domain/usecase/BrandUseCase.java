package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BrandUseCase  implements IBrandPersistencePort {

    // the 'final' makes the class inherently immutable, preventing potential issues related to concurrent modifications and ensuring thread safety.
    private final IBrandPersistencePort brandPersistencePort; //

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        this.brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Page<Brand> getAllBrands(Pageable pageable) {
        return brandPersistencePort.getAllBrands(pageable);
    }

    @Override
    public Brand getBrand(Long id) {
        return brandPersistencePort.getBrand(id);
    }

    @Override
    public void deleteBrand(Long id) {
        brandPersistencePort.deleteBrand(id);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandPersistencePort.updateBrand(brand);
    }



    @Override
    public Brand getBrandByName(String name) {
        return brandPersistencePort.getBrandByName(name);
    }
}
