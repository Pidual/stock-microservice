package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.exceptions.NoDataException;
import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.spi.IArticlePersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public CustomPage<Article> getArticlesForPagination(CustomPageRequest customPageRequest) {
        boolean ascending = customPageRequest.isAscending();
        Sort sort = ascending ? Sort.by("name").ascending() : Sort.by("name").descending();
        
        PageRequest pageRequest = PageRequest.of(customPageRequest.getPage(), customPageRequest.getSize(), sort);
        Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageRequest);

        return articleEntityMapper.toCustomPage(articleEntityPage.getContent(), articleEntityPage.getTotalPages(), articleEntityPage.getTotalElements());
    }


    @Override
    public List<Article> getAllArticles() {
        List<ArticleEntity> articleEntities = articleRepository.findAll();
        if(articleEntities.isEmpty()) {
            throw new NoDataException();
        }
        return articleEntityMapper.toArticleList(articleEntities);
    }


    @Override
    public void saveArticle(Article article) {
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }


    @Override
    public Article getArticle(String articleName) {
        return articleEntityMapper.toArticle(articleRepository.findByName(articleName));
    }





}
