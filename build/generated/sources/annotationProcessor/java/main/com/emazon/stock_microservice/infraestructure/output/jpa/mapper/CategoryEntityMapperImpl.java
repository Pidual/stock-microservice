package com.emazon.stock_microservice.infraestructure.output.jpa.mapper;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.infraestructure.output.jpa.entity.CategoryEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-19T10:38:48-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CategoryEntityMapperImpl implements CategoryEntityMapper {

    @Override
    public CategoryEntity toEntity(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName( category.getName() );
        categoryEntity.setDescription( category.getDescription() );
        categoryEntity.setId( category.getId() );

        return categoryEntity;
    }

    @Override
    public Category toCategory(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String name = null;
        String description = null;
        Long id = null;

        name = entity.getName();
        description = entity.getDescription();
        id = entity.getId();

        Category category = new Category( id, name, description );

        return category;
    }

    @Override
    public List<Category> toCategoryList(List<CategoryEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( entities.size() );
        for ( CategoryEntity categoryEntity : entities ) {
            list.add( toCategory( categoryEntity ) );
        }

        return list;
    }
}
