package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.ArticleDTO;
import com.emazon.stock_microservice.application.mapper.ArticleRequestMapper;
import com.emazon.stock_microservice.application.mapper.PageMapper;
import com.emazon.stock_microservice.domain.api.IArticleServicePort;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticleHandlerTest {

    @Mock
    private IArticleServicePort articleUseCase;

    @Mock
    private IBrandServicePort brandUseCase;

    @Mock
    private ICategoryServicePort categoryUseCase;

    @Mock
    private ArticleRequestMapper articleRequestMapper;

    @InjectMocks
    private ArticleHandler articleHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllArticlesPaged() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 3);
        Brand brand = new Brand(1L, "Nike", "Brand description");
        Set<Category> categories = new HashSet<>();
        categories.add(new Category(1L, "Ropa", "Category description"));

        Article article = new Article(
                1L,                  // id
                739.99,              // price
                200,                 // quantity
                "Zapato clásico",    // description
                "Nike Air Force1",   // name
                brand,               // brand
                categories           // categories
        );

        CustomPage<Article> customPage = new CustomPage<>(List.of(article), 1, 1, 0, true);

        when(articleUseCase.getAllArticlesPaged(any(CustomPageRequest.class))).thenReturn(customPage);
        when(articleRequestMapper.toArticleRequest(any(Article.class))).thenReturn(new ArticleDTO());

        // Act
        Page<ArticleDTO> result = articleHandler.getAllArticlesPaged(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalPages());
        verify(articleUseCase, times(1)).getAllArticlesPaged(any(CustomPageRequest.class));
    }

    @Test
    void testSaveArticle_Success() {
        // Arrange
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setBrandName("Nike");
        articleDTO.setCategoryNames(Set.of("Ropa", "Zapatos"));

        Brand brand = new Brand(1L, "Nike", "Brand description");
        Set<Category> categories = Set.of(
                new Category(1L, "Ropa", "Category description"),
                new Category(2L, "Zapatos", "Category description")
        );

        Article article = new Article(
                1L,                    // id
                739.99,                // price
                200,                   // quantity
                "Zapato clásico",      // description
                "Nike Air Force1",     // name
                brand,                 // brand
                categories             // categories
        );

        when(brandUseCase.getBrand("Nike")).thenReturn(brand);
        when(categoryUseCase.getCategory("Ropa")).thenReturn(new Category(1L, "Ropa", "Category description"));
        when(categoryUseCase.getCategory("Zapatos")).thenReturn(new Category(2L, "Zapatos", "Category description"));
        when(articleRequestMapper.toArticle(any(ArticleDTO.class))).thenReturn(article);

        // Act
        articleHandler.saveArticle(articleDTO);

        // Assert
        verify(articleUseCase, times(1)).saveArticle(any(Article.class));
    }

    @Test
    void testSaveArticle_DuplicateArticle() {
        // Arrange
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setName("Nike Air Force1");
        articleDTO.setCategoryNames(Set.of("Ropa")); // Asegúrate de inicializar categoryNames

        Brand brand = new Brand(1L, "Nike", "Brand description");
        Set<Category> categories = Set.of(new Category(1L, "Ropa", "Category description"));

        // Aquí se debe pasar todos los parámetros requeridos por el constructor
        Article existingArticle = new Article(
                1L,                    // id
                739.99,                // price
                100,                   // quantity
                "A description",       // description
                "Nike Air Force1",     // name
                brand,                 // brand
                categories             // categories
        );

        when(articleUseCase.getAllArticles()).thenReturn(Collections.singletonList(existingArticle));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            articleHandler.saveArticle(articleDTO);
        });

        String expectedMessage = "Duplicate article name detected";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void testGetAllArticles() {
        // Arrange
        Brand brand = new Brand(1L, "Nike", "Brand description");
        Set<Category> categories = new HashSet<>();
        categories.add(new Category(1L, "Ropa", "Category description"));

        // Usar el constructor completo de Article
        Article article = new Article(
                1L,                     // id
                739.99,                 // price
                200,                    // quantity
                "Zapato clásico",       // description
                "Nike Air Force1",      // name
                brand,                  // brand
                categories              // categories
        );

        when(articleUseCase.getAllArticles()).thenReturn(List.of(article));
        when(articleRequestMapper.toArticleRequest(any(Article.class))).thenReturn(new ArticleDTO());

        // Act
        List<ArticleDTO> result = articleHandler.getAllArticles();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(articleUseCase, times(1)).getAllArticles();
    }


}

