package com.emazon.stock_microservice.domain.spi;

import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;

import java.util.List;

public interface IArticlePersistencePort {

    void saveArticle(Article article);
    Article getArticle(String articleId);

    List<Article> getAllArticles();

    CustomPage<Article> getArticlesForPagination(CustomPageRequest customPageRequest);
}
