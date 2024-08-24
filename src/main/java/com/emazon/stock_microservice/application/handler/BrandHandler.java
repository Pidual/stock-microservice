package com.emazon.stock_microservice.application.handler;


import com.emazon.stock_microservice.application.dto.BrandRequest;
import com.emazon.stock_microservice.application.dto.CategoryRequest;
import com.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BrandHandler implements IBrandHandler {

    private IBrandServicePort brandServicePort;
    private BrandRequestMapper brandRequestMapper;

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
    public Page<BrandRequest> getAllBrands(Pageable pageable) {
        return brandServicePort.getAllBrands(pageable)
                .map(brandRequestMapper::toBrandRequest);
    }

    @Override
    public BrandRequest getBrandById(Long id) {
        return null;
    }
}
