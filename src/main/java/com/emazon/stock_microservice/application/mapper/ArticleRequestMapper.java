package com.emazon.stock_microservice.application.mapper;

import com.emazon.stock_microservice.application.dto.ArticleDTO;
import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper para transofrmar de Articulos a ArticulosDTO
 * Mapper para transofrmar de Dominio a Aplicacion
 *
 */

@Mapper(componentModel = "spring")
public interface ArticleRequestMapper {

    Article toArticle(ArticleDTO articleDTO);

    @Mapping(source = "brand.name", target = "brandName")
    @Mapping(source = "categories", target = "categoryNames")
    ArticleDTO toArticleDTO(Article article);

    // Método para mapear de Set<Category> a Set<String>
    default Set<String> mapCategoriesToCategoryNames(Set<Category> categories) {
        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }

}
