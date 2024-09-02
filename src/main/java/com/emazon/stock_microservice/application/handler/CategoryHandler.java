package com.emazon.stock_microservice.application.handler;

import com.emazon.stock_microservice.application.dto.CategoryDTO;
import com.emazon.stock_microservice.application.mapper.CategoryRequestMapper;
import com.emazon.stock_microservice.application.mapper.PageMapper;
import com.emazon.stock_microservice.domain.api.ICategoryServicePort;
import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryUseCase;
    private final CategoryRequestMapper categoryRequestMapper;

    @Override
    public Page<CategoryDTO> getAllCategoriesPaged(Pageable pageable) {
        //Criterio de ordenacion
        String sortBy = pageable.getSort().isSorted() ? pageable.getSort().toList().get(0).getProperty() : "name";
        boolean ascending = pageable.getSort().isSorted() ? pageable.getSort().toList().get(0).isAscending() : true;

        // de page.spring a page.domain
        CustomPageRequest customPageRequest = new CustomPageRequest(pageable.getPageNumber(),pageable.getPageSize(),ascending,sortBy);
        // get all the categories from the domain in page.domain format
        CustomPage<Category> customPage = categoryUseCase.getAllCategoriesPaged(customPageRequest);
        //then covert the custom page to page.spring
        return PageMapper.toSpringPage(new CustomPage<>(customPage.getContent().stream().map(categoryRequestMapper::toCategoryRequest).toList(),
                customPage.getTotalElements(),
                customPage.getTotalPages(),
                customPage.getCurrentPage(),
                customPage.isAscending()));
    }

    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        Category category = categoryRequestMapper.toCategory(categoryDTO);
        categoryUseCase.saveCategory(category);
    }

    @Override
    public CategoryDTO getCategory(String categoryName) {
        Category category = categoryUseCase.getCategory(categoryName);
        return categoryRequestMapper.toCategoryRequest(category);
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Category existingCategory = categoryUseCase.getCategory(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());
        categoryUseCase.updateCategory(existingCategory);
    }

    @Override
    public void deleteCategory(long id) {
        categoryUseCase.deleteCategoryById(id);
    }



    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        return categoryUseCase.getAllCategories().stream().map(categoryRequestMapper::toCategoryRequest).toList();
    }
}
