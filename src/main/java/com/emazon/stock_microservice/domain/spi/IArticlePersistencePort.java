package com.emazon.stock_microservice.domain.spi;

import com.emazon.stock_microservice.domain.model.Article;

import java.util.List;

public interface IArticlePersistencePort {

    void saveArticle(Article article);

    Article getArticle(String articleId);

    Article deleteArticle(String articleId);

    void updateArticle(Article article);

    List<Article> getAllArticles();
}
