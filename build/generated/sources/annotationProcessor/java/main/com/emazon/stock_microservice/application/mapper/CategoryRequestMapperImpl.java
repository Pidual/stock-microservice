package com.emazon.stock_microservice.application.mapper;

import com.emazon.stock_microservice.application.dto.CategoryRequest;
import com.emazon.stock_microservice.domain.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-24T18:20:24-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CategoryRequestMapperImpl implements CategoryRequestMapper {

    @Override
    public Category toCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        String name = null;
        String description = null;
        Long id = null;

        name = categoryRequest.getName();
        description = categoryRequest.getDescription();
        id = categoryRequest.getId();

        Category category = new Category( id, name, description );

        return category;
    }

    @Override
    public CategoryRequest toCategoryRequest(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryRequest categoryRequest = new CategoryRequest();

        categoryRequest.setName( category.getName() );
        categoryRequest.setDescription( category.getDescription() );
        categoryRequest.setId( category.getId() );

        return categoryRequest;
    }
}
