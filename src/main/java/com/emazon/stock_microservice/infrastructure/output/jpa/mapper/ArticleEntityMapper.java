package com.emazon.stock_microservice.infrastructure.output.jpa.mapper;

import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BrandEntityMapper.class})
public interface ArticleEntityMapper {

    ArticleEntity toArticleEntity(Article article);


    Article toArticle(ArticleEntity articleEntity);


    List<Article> toArticleList(List<ArticleEntity> articleEntities);
}
