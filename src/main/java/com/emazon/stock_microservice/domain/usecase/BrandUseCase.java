package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.exceptions.AlreadyExistsException;
import com.emazon.stock_microservice.domain.exceptions.NoDataException;
import com.emazon.stock_microservice.domain.exceptions.WrongDescriptionException;
import com.emazon.stock_microservice.domain.exceptions.WrongNameException;
import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;

import static com.emazon.stock_microservice.common.Constants.*;

import java.util.List;

public class BrandUseCase  implements IBrandServicePort {

    private final IBrandPersistencePort brandJpaAdapter; //brandJpa !!!

    public BrandUseCase(IBrandPersistencePort brandJpaAdapter) {
        this.brandJpaAdapter = brandJpaAdapter;
    }

    @Override
    public List<Brand> getAllBrands() {
        List<Brand> allBrands = brandJpaAdapter.getAllBrands();
        if(allBrands == null || allBrands.isEmpty()) {
            throw new NoDataException();
        }
        return allBrands;
    }

    @Override
    public CustomPage<Brand> getAllBrandsPaged(CustomPageRequest customPageRequest) {
        return brandJpaAdapter.getBrandsForPagination(customPageRequest);
    }

    @Override
    public Brand getBrand(String name) {
        if(brandJpaAdapter.getBrand(name) == null) {
            throw new WrongNameException(NOT_FOUND_ERROR);
        }
        return brandJpaAdapter.getBrand(name);
    }

    @Override
    public void saveBrand(Brand brand) {
        validateBrand(brand);
        if (brandJpaAdapter.getBrand(brand.getName()) != null) {
            throw new AlreadyExistsException(ALREADY_EXITS_ERROR);
        }
        this.brandJpaAdapter.saveBrand(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        validateBrand(brand);
        brandJpaAdapter.updateBrand(brand);
    }

    @Override
    public void deleteBrand(String name) {
        brandJpaAdapter.deleteBrand(name);
    }


    public static void validateBrand(Brand brand) {
        String name = brand.getName();
        if (name == null || name.isEmpty() || name.length() > 50) {
            throw new WrongNameException(WRONG_NAME_ERROR);
        }

        String description = brand.getDescription();
        if (description == null || description.isEmpty() || description.length() > 120) {
            throw new WrongDescriptionException(WRONG_DESCRIPTION_ERROR);
        }
    }
}
