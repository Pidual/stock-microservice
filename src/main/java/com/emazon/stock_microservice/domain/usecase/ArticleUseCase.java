package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.api.IArticleServicePort;
import com.emazon.stock_microservice.domain.exceptions.*;
import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.spi.IArticlePersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import jakarta.transaction.Transactional;

import java.util.List;

import static com.emazon.stock_microservice.common.Constants.*;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        validateArticle(article);
        if (articleExists(article.getName())) {
            throw new AlreadyExistsException(ARTICLE_ALREADY_EXISTS_ERROR);
        }
        articlePersistencePort.saveArticle(article);
    }


    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = articlePersistencePort.getAllArticles();
        if (articles == null || articles.isEmpty()) {
            throw new NoDataException();
        }
        return articles;
    }

    @Override
    public CustomPage<Article> getAllArticlesPaged(CustomPageRequest customPageRequest) {
        return articlePersistencePort.getArticlesForPagination(customPageRequest);
    }



    @Override
    @Transactional
    public void addStock(String articleName, int additionalStock) {
        validateStockAmount(additionalStock);
        Article article = getArticleByName(articleName);
        article.setQuantity(article.getQuantity() + additionalStock);
        articlePersistencePort.saveArticle(article);
    }

    private void validateStockAmount(int additionalStock) {
        if (additionalStock <= 0) {
            throw new StockException(ARTICLE_STOCK_MUST_BE_POSITIVE);
        }
    }

    private Article getArticleByName(String articleName) {
        Article article = articlePersistencePort.getArticle(articleName);
        if (article == null) {
            throw new ArticleNotFoundException(ARTICLE_NOT_FOUND_ERROR_TEXT + articleName);
        }
        return article;
    }

    private boolean articleExists(String articleName) {
        return articlePersistencePort.getArticle(articleName) != null;
    }

    private void validateArticle(Article article) {
        validateArticleName(article.getName());
        validateArticleDescription(article.getDescription());
    }

    private void validateArticleName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new WrongNameException("El nombre del artículo no puede estar vacío");
        }
    }

    private void validateArticleDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new WrongDescriptionException("La descripción del artículo no puede estar vacía");
        }
    }


}
