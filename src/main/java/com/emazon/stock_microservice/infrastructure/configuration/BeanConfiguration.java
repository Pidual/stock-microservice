package com.emazon.stock_microservice.infrastructure.configuration;


import com.emazon.stock_microservice.domain.api.IArticleServicePort;
import com.emazon.stock_microservice.domain.api.IBrandServicePort;
import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.spi.IArticlePersistencePort;
import com.emazon.stock_microservice.domain.spi.IBrandPersistencePort;
import com.emazon.stock_microservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_microservice.domain.usecase.ArticleUseCase;
import com.emazon.stock_microservice.domain.usecase.BrandUseCase;
import com.emazon.stock_microservice.domain.usecase.CategoryUseCase;
import com.emazon.stock_microservice.infrastructure.output.jpa.adapter.ArticleJpaAdapter;
import com.emazon.stock_microservice.infrastructure.output.jpa.adapter.BrandJpaAdapter;
import com.emazon.stock_microservice.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.IArticleRepository;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.IBrandRepository;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // CONFIGURATION -----
@RequiredArgsConstructor
public class BeanConfiguration { // this does the dependency inversion

    private final ICategoryRepository categoryRepository; //repo
    private final CategoryEntityMapper categoryEntityMapper; //mapper

    private final IBrandRepository brandRepository; //repo
    private final BrandEntityMapper brandEntityMapper; //mapper

    private final IArticleRepository articleRepository; //repo
    private final ArticleEntityMapper articleEntityMapper; //mapper

    // Category Configuration
    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    //Brand Configuration
    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    //Article Configuration
    @Bean
    public IArticleServicePort articleServicePort() {
        return new ArticleUseCase(articlePersistencePort());
    }

    @Bean
    public IArticlePersistencePort articlePersistencePort() {
        return new ArticleJpaAdapter(articleRepository,articleEntityMapper);
    }

}
















