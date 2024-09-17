package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;


import java.util.List;

public interface IArticleServicePort {

    void saveArticle(Article article);

    List<Article> getAllArticles();

    CustomPage<Article> getAllArticlesPaged(CustomPageRequest customPageRequest);


    void addStock(String articleName, int additionalStock);
}
