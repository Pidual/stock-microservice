package com.emazon.stock_microservice.infrastructure.output.jpa.mapper;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {


    CategoryEntity toEntity(Category category);

    Category toCategory(CategoryEntity entity);

    List<Category> toCategoryList(List<CategoryEntity> entities);
    CustomPage<Category> toCustomPage(List<CategoryEntity> content, int totalPages, long totalElements);

}
