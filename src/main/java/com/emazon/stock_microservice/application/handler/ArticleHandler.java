package com.emazon.stock_microservice.application.handler;

//import com.emazon.stock_microservice.application.dto.ArticleDTO;
//import com.emazon.stock_microservice.application.mapper.ArticleRequestMapper;
//import com.emazon.stock_microservice.domain.api.IArticleServicePort;
//import com.emazon.stock_microservice.domain.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {

    //private final IArticleServicePort articleUseCase;
    //private final ArticleRequestMapper articleRequestMapper;

//    @Override
//    public void saveArticle(ArticleDTO articleDTO) {
//        Article article = articleRequestMapper.toArticle(articleDTO);
//        article.validate(); // Validación de categorías
//        articleUseCase.saveArticle(article);
//    }

    // Otros métodos como getArticle, updateArticle, deleteArticle, etc.
}
