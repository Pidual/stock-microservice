package com.emazon.stock_microservice.application.mapper;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
import com.emazon.stock_microservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Category toCategory(CategoryRequest categoryRequest);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryRequest toCategoryRequest(Category category);
}