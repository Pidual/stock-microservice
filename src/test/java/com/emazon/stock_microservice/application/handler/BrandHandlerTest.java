package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandDTO;
import com.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.exceptions.AlreadyExistsException;
import com.emazon.stock_microservice.domain.exceptions.WrongDescriptionException;
import com.emazon.stock_microservice.domain.exceptions.WrongNameException;
import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandHandlerTest {

    @Mock
    private IBrandServicePort brandUseCase;

    @Mock
    private BrandRequestMapper brandRequestMapper;

    @InjectMocks
    private BrandHandler brandHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllBrandsPaged() {
        Pageable pageable = PageRequest.of(0, 2);
        Brand brand = new Brand(1L, "Brand1", "Description1");
        BrandDTO brandDTO = new BrandDTO("Brand1", "Description1");
        CustomPage<Brand> customPage = new CustomPage<>(List.of(brand), 1, 2, 0, true);
        when(brandUseCase.getAllBrandsPaged(any(CustomPageRequest.class))).thenReturn(customPage);
        when(brandRequestMapper.toBrandRequest(any(Brand.class))).thenReturn(brandDTO);
        Page<BrandDTO> result = brandHandler.getAllBrandsPaged(pageable);
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getTotalElements());
        verify(brandUseCase, times(1)).getAllBrandsPaged(any(CustomPageRequest.class));
    }

    @Test
    void shouldSaveBrandSuccessfully() {
        BrandDTO brandDTO = new BrandDTO("Brand1", "Description1");
        Brand brand = new Brand(1L, "Brand1", "Description1");
        when(brandRequestMapper.toBrand(any(BrandDTO.class))).thenReturn(brand);
        brandHandler.saveBrand(brandDTO);
        verify(brandUseCase, times(1)).saveBrand(brand);
    }

    @Test
    void shouldThrowExceptionWhenSavingBrandWithEmptyName() {
        BrandDTO brandDTO = new BrandDTO("", "Description1");
        Brand brand = new Brand(1L, "", "Description1");
        when(brandRequestMapper.toBrand(any(BrandDTO.class))).thenReturn(brand);
        doThrow(new WrongNameException("Brand name is empty")).when(brandUseCase).saveBrand(brand);
        assertThrows(WrongNameException.class, () -> brandHandler.saveBrand(brandDTO));
        verify(brandUseCase, times(1)).saveBrand(any(Brand.class));
    }

    @Test
    void shouldThrowExceptionWhenSavingBrandWithEmptyDescription() {
        BrandDTO brandDTO = new BrandDTO("Brand1", "");
        Brand brand = new Brand(1L, "Brand1", "");
        when(brandRequestMapper.toBrand(any(BrandDTO.class))).thenReturn(brand);
        doThrow(new WrongDescriptionException("Brand description is empty")).when(brandUseCase).saveBrand(brand);
        assertThrows(WrongDescriptionException.class, () -> brandHandler.saveBrand(brandDTO));
        verify(brandUseCase, times(1)).saveBrand(any(Brand.class));
    }

    @Test
    void shouldUpdateBrandSuccessfully() {
        BrandDTO brandDTO = new BrandDTO("Brand1", "New Description");
        Brand existingBrand = new Brand(1L, "Brand1", "Old Description");
        when(brandUseCase.getBrand(brandDTO.getName())).thenReturn(existingBrand);
        brandHandler.updateBrand(brandDTO);
        assertEquals("New Description", existingBrand.getDescription());
        verify(brandUseCase, times(1)).updateBrand(existingBrand);
    }

    @Test
    void shouldDeleteBrandSuccessfully() {
        BrandDTO brandDTO = new BrandDTO("Brand1", "Description1");

        brandHandler.deleteBrand(brandDTO);

        verify(brandUseCase, times(1)).deleteBrand(brandDTO.getName());
    }

    @Test
    void shouldGetBrandSuccessfully() {
        Brand brand = new Brand(1L, "Brand1", "Description1");
        BrandDTO brandDTO = new BrandDTO("Brand1", "Description1");

        when(brandUseCase.getBrand("Brand1")).thenReturn(brand);
        when(brandRequestMapper.toBrandRequest(brand)).thenReturn(brandDTO);

        BrandDTO result = brandHandler.getBrand("Brand1");

        assertEquals(brandDTO, result);
        verify(brandUseCase, times(1)).getBrand("Brand1");
    }

    @Test
    void shouldFindAllBrandsSuccessfully() {
        Brand brand = new Brand(1L, "Brand1", "Description1");
        BrandDTO brandDTO = new BrandDTO("Brand1", "Description1");

        when(brandUseCase.getAllBrands()).thenReturn(Arrays.asList(brand));
        when(brandRequestMapper.toBrandRequest(brand)).thenReturn(brandDTO);

        List<BrandDTO> result = brandHandler.findAllBrands();

        assertEquals(1, result.size());
        verify(brandUseCase, times(1)).getAllBrands();
    }

    @Test
    void shouldThrowExceptionWhenSavingExistingBrand() {
        BrandDTO brandDTO = new BrandDTO("ExistingBrand", "Description");
        Brand existingBrand = new Brand(1L, "ExistingBrand", "Description");

        when(brandRequestMapper.toBrand(any(BrandDTO.class))).thenReturn(existingBrand);
        doThrow(new AlreadyExistsException("Brand already exists")).when(brandUseCase).saveBrand(existingBrand);

        assertThrows(AlreadyExistsException.class, () -> brandHandler.saveBrand(brandDTO));
        verify(brandUseCase, times(1)).saveBrand(existingBrand);
    }

}