package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.infrastructure.exceptions.brand_expections.BrandAlreadyExistsException;
import com.emazon.stock_microservice.infrastructure.exceptions.brand_expections.BrandNotFoundException;
import com.emazon.stock_microservice.infrastructure.exceptions.category_expetions.InvalidCategoryException;
import com.emazon.stock_microservice.infrastructure.exceptions.NoDataException;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.BrandEntity;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


/**
 * que clase es esta? Implementa IBrandPersistencePort que esta en el dominio
 * Esta clase hace parte de la INFRAESTRUCTURA
 * Utiliza brandRepository
 * Utiliza brandMapper (este es para transformar objetos)
 */

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

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

    @Override
    public void saveBrand(Brand brand) {
        validateBrand(brand);
        if(brandRepository.findByName(brand.getName()).isPresent()){
            throw new BrandAlreadyExistsException();
        }
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }

    @Override
    public Brand getBrand(String brandName) {
        return brandEntityMapper.toBrand(brandRepository.findByName(brandName).orElseThrow(BrandNotFoundException::new));
    }


    @Override
    public void deleteBrand(String brandName) {
        BrandEntity brandToDelete = brandRepository.findByName(brandName).orElseThrow(BrandNotFoundException::new);
        brandRepository.delete(brandToDelete);
    }

    @Override
    public void updateBrand(Brand brand) {
        validateBrand(brand);
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));

    }

    @Override
    public List<Brand> getAllBrands() {
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        if(brandEntityList.isEmpty()){
            throw new NoDataException();
        }
        return brandEntityMapper.toBrandList(brandEntityList);
    }



}
