package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private IBrandRepository brandRepository;
    private BrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {

    }

    @Override
    public Page<Brand> getAllBrands(Pageable pageable) {
        return null;
    }

    @Override
    public Brand getBrand(Long id) {
        return null;
    }

    @Override
    public void deleteBrand(Long id) {

    }

    @Override
    public void updateBrand(Brand brand) {

    }

    @Override
    public Brand getBrandByName(String name) {
        return null;
    }
}
