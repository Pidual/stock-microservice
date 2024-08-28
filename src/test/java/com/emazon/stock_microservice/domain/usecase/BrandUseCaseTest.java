package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.application.handler.BrandHandler;
import com.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class BrandUseCaseTest {

    private IBrandPersistencePort brandPersistencePort;
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        brandServicePort = Mockito.mock(IBrandServicePort.class);
        brandRequestMapper = Mockito.mock(BrandRequestMapper.class);
        brandHandler = new BrandHandler(brandServicePort, brandRequestMapper);
    }


    @Test
    void saveBrand() {
        //Arrange
        Brand brand = new Brand(999999999999999999L,"TestBrand","test text test text test text test text");
        BrandUseCase brandUseCase = new BrandUseCase(brandJpaAdapter);

        //Act
        brandUseCase.saveBrand(brand);

        //Assert
        verify(brandJpaAdapter, times(1).saveBrand(brand));
    }



    @Test
    void getBrand() {
    }

    @Test
    void updateBrand() {
    }

    @Test
    void deleteBrand() {
    }

    @Test
    void getAllBrandsPaged() {
    }

    @Test
    void getAllBrands() {
    }
}