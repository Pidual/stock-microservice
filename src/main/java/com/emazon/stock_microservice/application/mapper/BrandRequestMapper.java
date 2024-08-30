package com.emazon.stock_microservice.application.mapper;


import com.emazon.stock_microservice.application.dto.BrandDTO;
import com.emazon.stock_microservice.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Clase utilizada por la capa APLICATION
 * Clase utilizada por el Handler para convertir de Request a Objetos de java
 * Implementa la libreria Mapstruct
 */

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Brand toBrand(BrandDTO brandDTO);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    BrandDTO toBrandRequest(Brand brand);



}
