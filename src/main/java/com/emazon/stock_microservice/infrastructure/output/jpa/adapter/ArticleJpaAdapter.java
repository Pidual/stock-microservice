package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.exceptions.NoDataException;
import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.spi.IArticlePersistencePort;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }


    @Override
    public Article getArticle(String articleName) {
        return articleEntityMapper.toArticle(articleRepository.findByName(articleName));
    }


    @Override
    public List<Article> getAllArticles() {
        List<ArticleEntity> articleEntities = articleRepository.findAll();
        if(articleEntities.isEmpty()) {
            throw new NoDataException();
        }
        return articleEntityMapper.toArticleList(articleEntities);
    }
}
