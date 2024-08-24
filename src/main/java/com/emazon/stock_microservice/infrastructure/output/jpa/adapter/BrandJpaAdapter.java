package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.infrastructure.exceptions.brand_expections.BrandNotFoundException;
import com.emazon.stock_microservice.infrastructure.exceptions.category_expetions.CategoryNotFoundException;
import com.emazon.stock_microservice.infrastructure.exceptions.category_expetions.InvalidCategoryException;
import com.emazon.stock_microservice.infrastructure.exceptions.category_expetions.NoDataException;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.BrandEntity;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        validateBrand(brand);
    }

    @Override
    public Page<Brand> getAllBrands(Pageable pageable) {
        Page<BrandEntity> categoryEntityPage = brandRepository.findAll(pageable);

        if (categoryEntityPage.isEmpty()) {
            throw new NoDataException();
        }
        return categoryEntityPage.map(brandEntityMapper::toBrand);
    }

    @Override
    public Brand getBrand(Long id) {
        return brandEntityMapper.toBrand(brandRepository.findById(id).orElseThrow(BrandNotFoundException::new));
    }

    @Override
    public Brand getBrandByName(String brandName) {
        return brandEntityMapper.toBrand(brandRepository.findByName(brandName).orElseThrow(BrandNotFoundException::new));
    }


    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public void updateBrand(Brand brand) {
        validateBrand(brand);
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));

    }



    private void validateBrand(Brand brand) {
        // Check if the category has a description
        if (brand.getDescription() == null || brand.getDescription().trim().isEmpty()) {
            throw new InvalidCategoryException("Brand description cannot be empty.");
        }
        // Ensure the name doesn't exceed 50 characters
        if (brand.getName().length() > 50) {
            throw new InvalidCategoryException("Brand name cannot exceed 50 characters.");
        }
        // Ensure the description doesn't exceed 90 characters
        if (brand.getDescription().length() > 90) {
            throw new InvalidCategoryException("Brand description cannot exceed 90 characters.");
        }
    }

}
