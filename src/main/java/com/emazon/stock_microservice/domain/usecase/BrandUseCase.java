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
import com.emazon.stock_microservice.domain.util.pageable.PageableUtil;


import java.util.List;

//  >>> ServicePort in the DOMAIN
public class BrandUseCase  implements IBrandServicePort {

    private final IBrandPersistencePort brandJpaAdapter; //brandJpa !!!

    private static final String WRONG_NAME_ERROR = "El nombre de marca no debe contener mas de 50 caracteres, estar vacio, o ser nullo";
    private static final String WRONG_DESCRIPTION_ERROR = "La descripcion de la marca no debe contener mas de 120 caracteres, estar vacio o ser nullo";
    private static final String ALREADY_EXITS_ERROR = "La marca ya existe";
    private static final String NOT_FOUND_ERROR = "No encontrada";


    // Persistence port
    public BrandUseCase(IBrandPersistencePort brandJpaAdapter) {
        this.brandJpaAdapter = brandJpaAdapter;
    }

    @Override
    public void saveBrand(Brand brand) {
        validateBrand(brand);
        // Check if the brand already exists
        if (brandJpaAdapter.getBrand(brand.getName()) != null) {
            throw new AlreadyExistsException(ALREADY_EXITS_ERROR);
        }
        this.brandJpaAdapter.saveBrand(brand);
    }

    @Override
    public Brand getBrand(String name) {
        if(brandJpaAdapter.getBrand(name) == null) {
            throw new WrongNameException(WRONG_NAME_ERROR);
        }
        return brandJpaAdapter.getBrand(name);
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

    @Override
    public CustomPage<Brand> getAllBrandsPaged(CustomPageRequest customPageRequest) {
        List<Brand> allBrands = brandJpaAdapter.getAllBrands();
        return PageableUtil.paginateAndSort(allBrands, customPageRequest, Brand::getName);
    }


    @Override
    public List<Brand> getAllBrands() {
        List<Brand> allBrands = brandJpaAdapter.getAllBrands();
        if(allBrands == null || allBrands.isEmpty()) {
            throw new NoDataException();
        }
        return allBrands;
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
