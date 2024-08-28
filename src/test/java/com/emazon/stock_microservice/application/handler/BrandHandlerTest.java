package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandDTO;
import com.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
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

    @Mock //mock es para laas clases que pide el handler
    private final IBrandServicePort brandUseCase; // useCase

    @Mock //mock es para las clases que usa la clase que estamos probando
    private final BrandRequestMapper brandRequestMapper; // mapper

    @InjectMocks // injectMocks es para injectar la clase que se esta probando
    private BrandHandler brandHandler;

    private Brand brand1; //test brand 1
    private Brand brand2; //test brand 2
    private BrandDTO brandDTO1; // test dto1
    private BrandDTO brandDTO2; //test dto2
    private List<Brand> brands; // a list of brands containing testBrands
    private CustomPage<Brand> customPage; // a CustomPage

    @BeforeEach //is used to signal that the annotated method should be executed before each test
    void setUp() {
        //Configura los mocks necesarios usando ⬇️
        MockitoAnnotations.openMocks(this);
        // Inicializar objetos comunes para los tests
        brand1 = new Brand(1L, "testBrand1", "Description1");
        brand2 = new Brand(2L, "testBrand2", "Description2");
        brandDTO1 = new BrandDTO("testBrand1", "Description1");
        brandDTO2 = new BrandDTO("testBrand2", "Description2");
        brands = Arrays.asList(brand1, brand2);
        customPage = new CustomPage<>(brands, brands.size(), 1, 0, true);
    }

    @Test
    void testGetAllBrandsPaged() {
        Pageable pageable = PageRequest.of(0, 5);

        when(brandUseCase.getAllBrandsPaged(any())).thenReturn(customPage);
        when(brandRequestMapper.toBrandRequest(brand1)).thenReturn(brandDTO1);
        when(brandRequestMapper.toBrandRequest(brand2)).thenReturn(brandDTO2);

        Page<BrandDTO> result = brandHandler.getAllBrandsPaged(pageable);

        assertEquals(2, result.getTotalElements());
        assertEquals(brandDTO1, result.getContent().get(0));
        assertEquals(brandDTO2, result.getContent().get(1));
    }

    @Test
    void testSaveBrand() {
        when(brandRequestMapper.toBrand(brandDTO1)).thenReturn(brand1);
        brandHandler.saveBrand(brandDTO1);
        verify(brandUseCase, times(1)).saveBrand(brand1);
    }


    //Comprueba que updateBrand actualiza correctamente una marca existente.
    @Test
    void testUpdateBrand() {
        BrandDTO brandDTO = new BrandDTO("Brand1", "Updated Description");
        Brand existingBrand = new Brand(1l,"Brand1", "Old Description");

        when(brandUseCase.getBrand("Brand1")).thenReturn(existingBrand);

        brandHandler.updateBrand(brandDTO);

        assertEquals("Updated Description", existingBrand.getDescription());
        verify(brandUseCase, times(1)).updateBrand(existingBrand);
    }


    //Verifica que deleteBrand invoque el método correspondiente en la capa de dominio.
    @Test
    void testDeleteBrand() {
        BrandDTO brandDTO = new BrandDTO("Brand1", "Description1");

        brandHandler.deleteBrand(brandDTO);

        verify(brandUseCase, times(1)).deleteBrand("Brand1");
    }


    //Asegura que se pueda recuperar una marca específica.
    @Test
    void testGetBrand() {
        when(brandUseCase.getBrand("testBrand1")).thenReturn(brand1);
        when(brandRequestMapper.toBrandRequest(brand1)).thenReturn(brandDTO1);
        BrandDTO result = brandHandler.getBrand("testBrand1");
        assertEquals(brandDTO1, result);
    }

    @Test
    void testGetAllBrands() {
        when(brandUseCase.getAllBrands()).thenReturn(brands);
        when(brandRequestMapper.toBrandRequest(brand1)).thenReturn(brandDTO1);
        when(brandRequestMapper.toBrandRequest(brand2)).thenReturn(brandDTO2);

        List<BrandDTO> result = brandHandler.getAllBrands();

        assertEquals(2, result.size());
        assertEquals(brandDTO1, result.get(0));
        assertEquals(brandDTO2, result.get(1));
    }
}