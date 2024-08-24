package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//  >>> ServicePort
public class BrandUseCase  implements IBrandServicePort {

    // the 'final' makes the class inherently immutable, preventing potential issues related to concurrent modifications and ensuring thread safety.
    private final IBrandPersistencePort brandPersistencePort; //

    // Persistence port
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
    public Brand getBrandById(Long id) {
        return brandPersistencePort.getBrand(id);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandPersistencePort.updateBrand(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
        brandPersistencePort.deleteBrand(id);
    }


}
