package com.emazon.stock_microservice.application.mapper;

import com.emazon.stock_microservice.domain.util.pageable.CustomPage;
import com.emazon.stock_microservice.domain.util.pageable.CustomPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageMapper {

    private PageMapper() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad, no se puede instanciar");
    }

    // Convert PageRequestCustom to Pageable de Spring
    public static Pageable toSpringPageable(CustomPageRequest customPageRequest) {

        return PageRequest.of(
                customPageRequest.getPage(),
                customPageRequest.getSize(),
                customPageRequest.isAscending() ? org.springframework.data.domain.Sort.Direction.ASC : org.springframework.data.domain.Sort.Direction.DESC,
                "name"); // Asume que se está ordenando por "name" copyright byLugos/StockMicroservice ©
    }

    // Converter PageCustom a Page de Spring
    public static <T> Page<T> toSpringPage(CustomPage<T> customPage) {
        Pageable pageable = PageRequest.of(customPage.getCurrentPage(), customPage.getContent().size());
        return new PageImpl<>(customPage.getContent(), pageable, customPage.getTotalElements());
    }
}