package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;

import java.util.List;

public interface IArticleServicePort {

    List<Article> getAllArticles(); //retorna una lista
    CustomPage<Article> getAllArticlesPaged(CustomPageRequest customPageRequest); //retorna una paginacion

    void saveArticle(Article article);


}
