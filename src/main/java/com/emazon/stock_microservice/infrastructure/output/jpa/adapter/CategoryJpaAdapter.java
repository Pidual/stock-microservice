package com.emazon.stock_microservice.infrastructure.output.jpa.adapter;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_microservice.domain.exceptions.NoDataException;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import com.emazon.stock_microservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_microservice.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_microservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;


@RequiredArgsConstructor
// This class communicates with the spi package that is in DOMAIN
// It has the implementations of how to use the database I guess
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if(categoryEntityList.isEmpty()){
            throw new NoDataException();
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public CustomPage<Category> getCategoriesForPagination(CustomPageRequest customPageRequest) {
        boolean ascending = customPageRequest.isAscending();
        Sort sort = ascending ? Sort.by("name").ascending() : Sort.by("name").descending(); // el sorting
        PageRequest pageRequest = PageRequest.of(customPageRequest.getPage(), customPageRequest.getSize(), sort); // una page request para pedirle a la base de datos
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageRequest);
        return categoryEntityMapper.toCustomPage(categoryEntityPage.getContent(), categoryEntityPage.getTotalPages(),categoryEntityPage.getTotalElements());
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Category getCategory(String name) {
        return categoryEntityMapper.toCategory(categoryRepository.findByName(name));
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
