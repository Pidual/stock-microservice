package com.emazon.stock_microservice.application.mapper;

import com.emazon.stock_microservice.application.dto.CategoryDTO;
import com.emazon.stock_microservice.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {

    Category toCategory(CategoryDTO categoryDTO);

    CategoryDTO toCategoryRequest(Category category);
}
