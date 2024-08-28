package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.domain.exceptions.brand_expections.BrandAlreadyExistsException;
import com.emazon.stock_microservice.domain.exceptions.brand_expections.BrandNotFoundException;
import com.emazon.stock_microservice.domain.exceptions.category_expetions.InvalidCategoryException;
import com.emazon.stock_microservice.domain.exceptions.NoDataException;
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



    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }

    @Override
    public Brand getBrand(String brandName) {
        return brandEntityMapper.toBrand(brandRepository.findByName(brandName));
    }


    @Override
    public void deleteBrand(String brandName) {
        BrandEntity brandToDelete = brandRepository.findByName(brandName);
        brandRepository.delete(brandToDelete);
    }

    @Override
    public void updateBrand(Brand brand) {
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
