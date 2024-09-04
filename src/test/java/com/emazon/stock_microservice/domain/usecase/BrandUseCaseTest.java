package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.exceptions.AlreadyExistsException;
import com.emazon.stock_microservice.domain.exceptions.NoDataException;
import com.emazon.stock_microservice.domain.exceptions.WrongDescriptionException;
import com.emazon.stock_microservice.domain.exceptions.WrongNameException;
import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    private Brand brand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        brand = new Brand(1L, "Nike", "Brand description");
    }

    @Test
    void getAllBrands_ShouldReturnBrands_WhenBrandsExist() {
        // Arrange
        when(brandPersistencePort.getAllBrands()).thenReturn(List.of(brand));

        // Act
        List<Brand> result = brandUseCase.getAllBrands();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(brand, result.get(0));
        verify(brandPersistencePort, times(1)).getAllBrands();
    }

    @Test
    void getAllBrands_ShouldThrowNoDataException_WhenNoBrandsExist() {
        // Arrange
        when(brandPersistencePort.getAllBrands()).thenReturn(List.of());

        // Act & Assert
        assertThrows(NoDataException.class, () -> brandUseCase.getAllBrands());
    }

    @Test
    void getAllBrandsPaged_ShouldReturnPagedBrands() {
        // Arrange
        CustomPageRequest pageRequest = new CustomPageRequest(0, 10, true, "name");
        CustomPage<Brand> expectedPage = new CustomPage<>(List.of(brand), 1, 1, 0, true);
        when(brandPersistencePort.getBrandsForPagination(pageRequest)).thenReturn(expectedPage);

        // Act
        CustomPage<Brand> result = brandUseCase.getAllBrandsPaged(pageRequest);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPage, result);
        verify(brandPersistencePort, times(1)).getBrandsForPagination(pageRequest);
    }

    @Test
    void getBrand_ShouldReturnBrand_WhenBrandExists() {
        // Arrange
        when(brandPersistencePort.getBrand(brand.getName())).thenReturn(brand);

        // Act
        Brand result = brandUseCase.getBrand(brand.getName());

        // Assert
        assertNotNull(result);
        assertEquals(brand, result);
        verify(brandPersistencePort, times(2)).getBrand(brand.getName());
    }

    @Test
    void getBrand_ShouldThrowWrongNameException_WhenBrandDoesNotExist() {
        // Arrange
        when(brandPersistencePort.getBrand(brand.getName())).thenReturn(null);

        // Act & Assert
        assertThrows(WrongNameException.class, () -> brandUseCase.getBrand(brand.getName()));
    }

    @Test
    void saveBrand_ShouldSaveBrand_WhenValid() {
        // Arrange
        when(brandPersistencePort.getBrand(brand.getName())).thenReturn(null);

        // Act
        brandUseCase.saveBrand(brand);

        // Assert
        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void saveBrand_ShouldThrowAlreadyExistsException_WhenBrandAlreadyExists() {
        // Arrange
        when(brandPersistencePort.getBrand(brand.getName())).thenReturn(brand);

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> brandUseCase.saveBrand(brand));
        verify(brandPersistencePort, never()).saveBrand(brand);
    }

    @Test
    void updateBrand_ShouldUpdateBrand_WhenValid() {
        // Act
        brandUseCase.updateBrand(brand);

        // Assert
        verify(brandPersistencePort, times(1)).updateBrand(brand);
    }

    @Test
    void deleteBrand_ShouldDeleteBrand_WhenBrandExists() {
        // Act
        brandUseCase.deleteBrand(brand.getName());

        // Assert
        verify(brandPersistencePort, times(1)).deleteBrand(brand.getName());
    }

    @Test
    void validateBrand_ShouldThrowWrongNameException_WhenNameIsInvalid() {
        // Arrange
        brand.setName("");  // Invalid name

        // Act & Assert
        assertThrows(WrongNameException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void validateBrand_ShouldThrowWrongDescriptionException_WhenDescriptionIsInvalid() {
        // Arrange
        brand.setDescription("");  // Invalid description

        // Act & Assert
        assertThrows(WrongDescriptionException.class, () -> brandUseCase.saveBrand(brand));
    }
}
