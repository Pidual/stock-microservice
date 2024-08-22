package com.emazon.stock_microservice.application.handler;


import com.emazon.stock_microservice.application.dto.BrandRequest;
import com.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.model.Brand;
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
    public void deleteBrandById(BrandRequest brandRequest) {
        brandServicePort.deleteBrandById(brandRequest.getId());
    }

//    @Override TODO:
//    public List<Brand> getAllBrands() {
//        return List.of();
//    }

    @Override
    public Brand getBrandById(Long id) {
        return null;
    }
}
