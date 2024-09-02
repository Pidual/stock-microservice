package com.emazon.stock_microservice.application.handler;


import com.emazon.stock_microservice.application.dto.ArticleDTO;
import com.emazon.stock_microservice.application.mapper.ArticleRequestMapper;
import com.emazon.stock_microservice.domain.api.IArticleServicePort;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * So what does the handler do?
 *
 */

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {

    //useCases
    private final IArticleServicePort articleUseCase;
    private final IBrandServicePort brandUseCase;
    private final ICategoryServicePort categoryUseCase;

    private final ArticleRequestMapper articleRequestMapper;


    @Override
    public Page<ArticleDTO> getAllArticlesPaged(Pageable pageable) {
        // For close future
        return null;
    }


    @Override
    public void saveArticle(ArticleDTO articleDTO) {
        //obtener la categoria(s)
        Set<Category> categories = articleDTO.getCategoryNames().stream().map(categoryUseCase::getCategory).collect(Collectors.toSet());

        //obtener la marca
        Brand brand = brandUseCase.getBrand(articleDTO.getBrandName());

        //Crear un articulo al cual le vamos a asignar la marca y categorias
        Article article = articleRequestMapper.toArticle(articleDTO);
        article.setCategories(categories);
        article.setBrand(brand);

        //Guardar el articulo
        articleUseCase.saveArticle(article);
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        // Obtain articles from the domain
        List<Article> articles = articleUseCase.getAllArticles();
        return articles.stream().map(articleRequestMapper::toArticleDTO).toList();
    }



}
