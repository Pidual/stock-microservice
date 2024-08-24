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

@Service
@RequiredArgsConstructor //los constructores importante para la configuracion del bean y poner final en los atributos
@Transactional //Spring will automatically manage the transaction for that method,
// ensuring that any database operations performed within it are part of a single transaction.
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
    public Page<BrandRequest> getAllBrands(Pageable pageable) {
        return brandServicePort.getAllBrands(pageable)
                .map(brandRequestMapper::toBrandRequest);
    }

    @Override
    public BrandRequest getBrandById(Long id) {
        return null;
    }
}
