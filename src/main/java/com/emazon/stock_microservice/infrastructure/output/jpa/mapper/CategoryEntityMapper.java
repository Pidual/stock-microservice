package com.emazon.stock_microservice.infrastructure.output.jpa.mapper;


import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface CategoryEntityMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "id", target = "id")
    CategoryEntity toEntity(Category category);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "id", target = "id")
    Category toCategory(CategoryEntity entity);


    //List<Category> toCategoryList(Pageable pageable); //this should work for the love of god
}
