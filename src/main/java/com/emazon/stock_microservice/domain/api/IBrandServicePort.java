package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;

import java.util.List;

/**
 * Esta interfaz se encuentra en la capa de DOMINIO
 * ESTA INTERFAZ ES UTILIZADA POR EL HANDLER QUE ESTA EN LA CAPA DE APLICACION
 * MODOFICAR ESTO IMPLICA MODIFICAR EL BrandHandler de aplicacion
 *
 */

public interface IBrandServicePort {

    void saveBrand(Brand brand);

    Brand getBrand(String brandName);

    void deleteBrand(String brandName);

    void updateBrand(Brand brand);

    CustomPage<Brand> getAllBrandsPaged(CustomPageRequest customPageRequest);

    List<Brand> getAllBrands();


}
