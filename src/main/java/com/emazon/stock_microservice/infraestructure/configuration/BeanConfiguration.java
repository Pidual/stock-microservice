package com.emazon.stock_microservice.infraestructure.configuration;


import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_microservice.domain.usecase.CategoryUseCase;
import com.emazon.stock_microservice.infraestructure.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock_microservice.infraestructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_microservice.infraestructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // CONFIGURACION -----
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository; //repo
    private final CategoryEntityMapper categoryEntityMapper; //mapper

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }


}
