package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.domain.exceptions.NoDataException;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.BrandEntity;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * implemented by the useCase in the domain layer
 * Esta clase hace parte de la INFRAESTRUCTURA
 * Utiliza brandRepository
 * Utiliza brandMapper (este es para transformar objetos)
 */

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public List<Brand> getAllBrands() {
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        if(brandEntityList.isEmpty()){
            throw new NoDataException();
        }
        return brandEntityMapper.toBrandList(brandEntityList);
    }

    @Override
    public CustomPage<Brand> getBrandsForPagination(CustomPageRequest customPageRequest) {
        boolean ascending = customPageRequest.isAscending();
        Sort sort = ascending ? Sort.by("name").ascending() : Sort.by("name").descending();
        PageRequest pageRequest = PageRequest.of(customPageRequest.getPage(), customPageRequest.getSize(), sort);
        Page<BrandEntity> brandEntityPage = brandRepository.findAll(pageRequest);
        return brandEntityMapper.toCustomPage(brandEntityPage.getContent(), brandEntityPage.getTotalPages(), brandEntityPage.getTotalElements());
    }


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










}
