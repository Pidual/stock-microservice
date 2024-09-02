package com.emazon.stock_microservice.domain.api;

import com.emazon.stock_microservice.domain.model.Article;

import java.util.List;

public interface IArticleServicePort {

    void saveArticle(Article article);

    List<Article> getAllArticles();

}
