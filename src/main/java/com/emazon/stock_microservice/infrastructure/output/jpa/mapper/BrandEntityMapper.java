package com.emazon.stock_microservice.infrastructure.output.jpa.mapper;

import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {

    BrandEntity toBrandEntity(Brand brand);
    Brand toBrand(BrandEntity brandEntity);

    List<Brand> toBrandList(List<BrandEntity> brandEntityList);
    CustomPage<Brand> toCustomPage(List<BrandEntity> content, int totalPages, long totalElements);

}





