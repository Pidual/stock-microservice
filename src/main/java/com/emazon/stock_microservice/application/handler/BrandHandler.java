package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandDTO;
import com.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.emazon.stock_microservice.application.mapper.PageMapper;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.model.Brand;

import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private final IBrandServicePort brandUseCase; // useCase
    private final BrandRequestMapper brandRequestMapper; // mapper

    //raid boss method special thanks to ian for the help
    @Override
    public Page<BrandDTO> getAllBrandsPaged(Pageable pageable) {
       // de page.spring a page.domain
        CustomPageRequest  customPageRequest = new CustomPageRequest(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort().isSorted());
        // get all the brands from the domain in page.domain format
        CustomPage<Brand> customPage = brandUseCase.getAllBrandsPaged(customPageRequest);
        // then coverts from page.domain to page.spring
        return PageMapper.toSpringPage(
                new CustomPage<>(customPage.getContent().stream().map(brandRequestMapper::toBrandRequest).toList(),
                        customPage.getTotalElements(),
                        customPage.getTotalPages(),
                        customPage.getCurrentPage(),
                        customPage.isAscending()));
    }

    @Override
    public void saveBrand(BrandDTO brandDTO) {
        Brand brand = brandRequestMapper.toBrand(brandDTO);
        brandUseCase.saveBrand(brand);
    }

    public void updateBrand(BrandDTO brandDTO) {
        Brand existingBrand = brandUseCase.getBrand(brandDTO.getName());
        existingBrand.setName(brandDTO.getName()); //Changes the name
        existingBrand.setDescription(brandDTO.getDescription()); //Changes the description
        brandUseCase.updateBrand(existingBrand); //saves the changes
    }

    @Override
    public void deleteBrand(BrandDTO brandDTO) {
        brandUseCase.deleteBrand(brandDTO.getName());
    }

    @Override
    public BrandDTO getBrand(String brandName) {
        return brandRequestMapper.toBrandRequest(brandUseCase.getBrand(brandName));
    }


    // .stream(): This converts the list of brand requests into a stream, which provides a convenient way to perform operations on the elements.
    // .map(brandRequestMapper::toBrandRequest): This applies the toBrandRequest method from the brandRequestMapper object to each element in the stream.
    // .collect(Collectors.toList()): This collects the transformed brand requests into a new list and returns it.
    @Override
    public List<BrandDTO> getAllBrands() {
        return brandUseCase.getAllBrands()
                .stream()
                .map(brandRequestMapper::toBrandRequest).toList();
    }


}
