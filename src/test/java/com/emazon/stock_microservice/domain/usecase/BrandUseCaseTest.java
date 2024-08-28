package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import com.emazon.stock_microservice.domain.util.pageable.PageableUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * @Mock que hace?
 * Esta anotación se utiliza para crear e inyectar un mock (un objeto simulado) de una clase o interfaz.
 * Los mocks son objetos que imitan el comportamiento de clases reales para que puedas probar la lógica sin depender de implementaciones concretas
 * o del estado real de los objetos.
 *
 *
 * @InjectMocks que hace?
 * Inyecta los mocks creados con @Mock (o los proporcionados manualmente) en el objeto anotado con @InjectMocks.
 * Este objeto será el que estás probando.
 * Mockito intentará inyectar los mocks en los campos de este objeto usando inyección de dependencias
 * (por constructor, setter, o directamente en el campo).
 *
 * MockitoAnnotations.openMocks(this):
 * Inicializa los mocks anotados con @Mock y @InjectMocks.
 * Debes llamar a esto antes de empezar tus tests,
 * generalmente en un método con @BeforeEach para asegurarte de que los mocks estén preparados antes de cada prueba.
 *
 * when(...).thenReturn(...):
 * Se utiliza para definir el comportamiento de un mock cuando se llama un metodo especifico
 * 'when' configurta el mock para que cuando ese metodo especifico sea llamado con ciertos argumentos retorne un valor determinado
 *
 *  verify(...)
 *  Se utiliza para verificar que un metodo en el mock fue llamado con ciertos argumentos y cuantas veces fue llamado.
 *   Esto es útil para asegurarse de que el código bajo prueba está interactuando con sus dependencias de la manera esperada.
 *
 *
 * assertEquals(...) (de JUnit):
 * Qué hace: Se utiliza para verificar que el valor esperado y el valor real son iguales.
 * Esto es parte de JUnit, no de Mockito, y se usa para hacer aserciones en los tests.
 */

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandJpaAdapter;

    @InjectMocks
    private BrandUseCase brandUseCase;

    // instancias de brand para usar en pruebas
    private Brand brand1;
    private Brand brand2;
    private List<Brand> brands;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // para inicializar los mocks antes de cada prueba.
        //Start test objects
        brand1 = new Brand(1L,"testBrand1","Description1");
        brand2 = new Brand(2L,"testBrand2","Description2");
        brands = Arrays.asList(brand1,brand2);
    }


    // Verifica que saveBrand en IBrandPersistencePort sea llamado una vez cuando se llama a saveBrand en BrandUseCase.
    @Test
    void testSaveBrand() {
        brandUseCase.saveBrand(brand1);
        verify(brandJpaAdapter, times(1)).saveBrand(brand1);
    }

    @Test
    void testSaveBrand_shouldThrowException_whenNameIsTooLong() {
        // Crear una marca con un nombre que exceda los 50 caracteres
        Brand brandWithLongName = new Brand(1L, "NombreDeMarcaDemasiadoLargoooooMUYMUYLARGOlargisimoTalVezEraUnNombreAlmeanOAlgo", "Description");

        // Intentar guardar la marca y verificar que se lance una excepción
        assertThrows(IllegalArgumentException.class, () -> brandUseCase.saveBrand(brandWithLongName));
    }

    //Simula la obtención de una marca específica y verifica que el método getBrand de IBrandPersistencePort se llame correctamente y devuelva el objeto esperado.
    @Test
    void testGetBrand() {
        when(brandJpaAdapter.getBrand("Brand1")).thenReturn(brand1);
        Brand result = brandUseCase.getBrand("Brand1");
        assertEquals(brand1, result);
        verify(brandJpaAdapter, times(1)).getBrand("Brand1");
    }

    // Asegura que updateBrand en IBrandPersistencePort sea llamado una vez cuando se llama a updateBrand en BrandUseCase.
    @Test
    void testUpdateBrand() {
        brandUseCase.updateBrand(brand1);
        verify(brandJpaAdapter, times(1)).updateBrand(brand1);
    }

    // Verifica que el método deleteBrand en IBrandPersistencePort sea llamado con el nombre correcto.
    @Test
    void testDeleteBrand() {
        brandUseCase.deleteBrand("Brand1");
        verify(brandJpaAdapter, times(1)).deleteBrand("Brand1");
    }

    // Simula la obtención de todas las marcas y su paginación, verificando que el método getAllBrandsPaged devuelva una CustomPage correctamente paginada y ordenada.
    @Test
    void testGetAllBrandsPaged() {
        when(brandJpaAdapter.getAllBrands()).thenReturn(brands);

        CustomPageRequest customPageRequest = new CustomPageRequest(0, 2, true);
        CustomPage<Brand> expectedPage = PageableUtil.paginateAndSort(brands, customPageRequest, Brand::getName);

        CustomPage<Brand> result = brandUseCase.getAllBrandsPaged(customPageRequest);

        assertEquals(expectedPage.getContent(), result.getContent());
        assertEquals(expectedPage.getTotalElements(), result.getTotalElements());
        assertEquals(expectedPage.getTotalPages(), result.getTotalPages());
        verify(brandJpaAdapter, times(1)).getAllBrands();
    }

    // Verifica que getAllBrands retorne la lista completa de marcas y que se llame a getAllBrands en IBrandPersistencePort.
    @Test
    void testGetAllBrands() {
        when(brandJpaAdapter.getAllBrands()).thenReturn(brands);
        List<Brand> result = brandUseCase.getAllBrands();
        assertEquals(brands, result);
        verify(brandJpaAdapter, times(1)).getAllBrands();
    }
}