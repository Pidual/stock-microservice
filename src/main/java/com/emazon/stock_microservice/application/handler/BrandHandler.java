package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandRequest;
import com.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.model.Brand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Que es esta clase?
 * es una parte de la capa aplication
 * ESTA CLASE ACTUA COMO INTERMEDIARIO ENTRE LA CAPA DE DOMINIO
 * Y LAS INTERFACES EXTERNAS
 * brandServicePort: es el use case que tenemos en el dominio
 * brandRequestMapper: es un mapper que transforma de Request a Objeto
 */

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }

    public void updateBrand(BrandRequest brandRequest) {
        Brand existingBrand = brandServicePort.getBrandById(brandRequest.getId());
        existingBrand.setName(brandRequest.getName()); //Changes the name
        existingBrand.setDescription(brandRequest.getDescription()); //Changes the description
        brandServicePort.updateBrand(existingBrand); //saves the changes
    }

    @Override
    public void deleteBrand(BrandRequest brandRequest) {
        brandServicePort.deleteBrandById(brandRequest.getId());
    }

    @Override
    public BrandRequest getBrandById(Long id) {
        return null;
    }

    //this is paged
    @Override
    public Page<BrandRequest> getAllBrandsPaged(Pageable pageable) {
        return brandServicePort.getAllBrandsPaged(pageable).map(brandRequestMapper::toBrandRequest);
    }

    // .stream(): This converts the list of brand requests into a stream, which provides a convenient way to perform operations on the elements.
    // .map(brandRequestMapper::toBrandRequest): This applies the toBrandRequest method from the brandRequestMapper object to each element in the stream.
    // .collect(Collectors.toList()): This collects the transformed brand requests into a new list and returns it.
    @Override
    public List<BrandRequest> getAllBrands() {
        return brandServicePort.getAllBrands()
                .stream()
                .map(brandRequestMapper::toBrandRequest).collect(Collectors.toList());
    }


}
