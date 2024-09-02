package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.ArticleDTO;
import com.emazon.stock_microservice.domain.model.Article;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * * This is the interface of article handler
 * it's implemented in the application layer
 * it's called in the rest controller
 */
public interface IArticleHandler {

    Page<ArticleDTO> getAllArticlesPaged(Pageable pageable);

    void saveArticle(ArticleDTO article);

    List<ArticleDTO> getAllArticles();


}
