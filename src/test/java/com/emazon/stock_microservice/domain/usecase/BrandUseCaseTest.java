package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class BrandUseCaseTest {


    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @Test
    void getAllBrands() {

    }

    @Test
    void getAllBrandsPaged() {
    }

    @Test
    void getBrand() {
    }

    @Test
    void saveBrand() {
    }

    @Test
    void updateBrand() {
    }

    @Test
    void deleteBrand() {
    }

    @Test
    void validateBrand() {
    }
}