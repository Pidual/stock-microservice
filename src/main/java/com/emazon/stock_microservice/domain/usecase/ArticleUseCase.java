package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.api.IArticleServicePort;
import com.emazon.stock_microservice.domain.exceptions.AlreadyExistsException;
import com.emazon.stock_microservice.domain.exceptions.NoDataException;
import com.emazon.stock_microservice.domain.exceptions.WrongDescriptionException;
import com.emazon.stock_microservice.domain.exceptions.WrongNameException;
import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.spi.IArticlePersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;


import java.util.List;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articleJpaAdapter;

    public ArticleUseCase(IArticlePersistencePort articleJpaAdapter) {
        this.articleJpaAdapter = articleJpaAdapter;
    }


    @Override
    public CustomPage<Article> getAllArticlesPaged(CustomPageRequest customPageRequest) {
        return articleJpaAdapter.getArticlesForPagination(customPageRequest);
    }


    @Override
    public void saveArticle(Article article) {
            validateArticle(article);
            if (articleJpaAdapter.getArticle(article.getName()) != null) {
                throw new AlreadyExistsException("Articulo ya existe");
            }
            articleJpaAdapter.saveArticle(article);
        }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = articleJpaAdapter.getAllArticles();
        if (articles == null || articles.isEmpty()) {
            throw new NoDataException();
        }
        return articles;
    }



    private void validateArticle(Article article) {
        if (article.getName() == null || article.getName().isEmpty()) {
            throw new WrongNameException("Articulo con nombre erroeno");
        }
        if (article.getDescription() == null || article.getDescription().isEmpty()) {
            throw new WrongDescriptionException("Articulo con describe erroeno");
        }
    }

}
