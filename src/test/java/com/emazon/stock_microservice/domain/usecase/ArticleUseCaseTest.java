package com.emazon.stock_microservice.domain.usecase;

import com.emazon.stock_microservice.domain.exceptions.AlreadyExistsException;
import com.emazon.stock_microservice.domain.exceptions.NoDataException;
import com.emazon.stock_microservice.domain.exceptions.WrongDescriptionException;
import com.emazon.stock_microservice.domain.exceptions.WrongNameException;
import com.emazon.stock_microservice.domain.model.Article;
import com.emazon.stock_microservice.domain.model.Brand;
import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.spi.IArticlePersistencePort;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    private Article article;
    private Brand validBrand;
    private Set<Category> validCategories;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validBrand = new Brand(1L, "Nike", "Zapatos melos a 120k");
        validCategories = new HashSet<>();
        validCategories.add(new Category(1L, "Ropa", "Category description"));

        article = new Article(1L, 100.0, 10, "Valid description", "Valid name", validBrand, validCategories);
    }

    @Test
    void getAllArticlesPaged_ShouldReturnCustomPage() {
        // Arrange
        CustomPageRequest pageRequest = new CustomPageRequest(0, 10, true, "name");
        CustomPage<Article> expectedPage = new CustomPage<>(List.of(article), 1, 1, 0, true);
        when(articlePersistencePort.getArticlesForPagination(pageRequest)).thenReturn(expectedPage);

        // Act
        CustomPage<Article> result = articleUseCase.getAllArticlesPaged(pageRequest);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPage, result);
        verify(articlePersistencePort, times(1)).getArticlesForPagination(pageRequest);
    }

    @Test
    void saveArticle_ShouldSaveArticle_WhenValid() {
        // Arrange
        when(articlePersistencePort.getArticle(article.getName())).thenReturn(null);

        // Act
        articleUseCase.saveArticle(article);

        // Assert
        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    void saveArticle_ShouldThrowAlreadyExistsException_WhenArticleExists() {
        // Arrange
        when(articlePersistencePort.getArticle(article.getName())).thenReturn(article);

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> articleUseCase.saveArticle(article));
        verify(articlePersistencePort, never()).saveArticle(article);
    }

    @Test
    void saveArticle_ShouldThrowWrongNameException_WhenNameIsInvalid() {
        // Arrange
        article.setName("");  // Invalid name

        // Act & Assert
        assertThrows(WrongNameException.class, () -> articleUseCase.saveArticle(article));
        verify(articlePersistencePort, never()).saveArticle(article);
    }

    @Test
    void saveArticle_ShouldThrowWrongDescriptionException_WhenDescriptionIsInvalid() {
        // Arrange
        article.setDescription("");  // Invalid description

        // Act & Assert
        assertThrows(WrongDescriptionException.class, () -> articleUseCase.saveArticle(article));
        verify(articlePersistencePort, never()).saveArticle(article);
    }

    @Test
    void getAllArticles_ShouldReturnArticles_WhenArticlesExist() {
        // Arrange
        when(articlePersistencePort.getAllArticles()).thenReturn(List.of(article));

        // Act
        List<Article> result = articleUseCase.getAllArticles();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(article, result.get(0));
        verify(articlePersistencePort, times(1)).getAllArticles();
    }

    @Test
    void getAllArticles_ShouldThrowNoDataException_WhenNoArticlesExist() {
        // Arrange
        when(articlePersistencePort.getAllArticles()).thenReturn(List.of());

        // Act & Assert
        assertThrows(NoDataException.class, () -> articleUseCase.getAllArticles());
    }
}
