package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.BrandDTO;
import com.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.emazon.stock_microservice.application.mapper.PageMapper;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.model.Brand;

import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandUseCase;
    private final BrandRequestMapper brandRequestMapper;

    @Override
    public Page<BrandDTO> getAllBrandsPaged(Pageable pageable) {
        // criteori ode ordenacion
        String sortBy = pageable.getSort().isSorted() ? pageable.getSort().toList().get(0).getProperty() : "name";
        boolean ascending = pageable.getSort().isSorted() ? pageable.getSort().toList().get(0).isAscending() : true;

       // de page.spring a page.domain
        CustomPageRequest  customPageRequest = new CustomPageRequest(pageable.getPageNumber(),pageable.getPageSize(),ascending,sortBy);
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

    @Override
    @Transactional(readOnly = true) // for more performance
    public List<BrandDTO> findAllBrands() {
        return brandUseCase.getAllBrands().stream().map(brandRequestMapper::toBrandRequest).toList();
    }


}
