package com.emazon.stock_microservice.application.dto;

import com.emazon.stock_microservice.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private String name;
    private String description;
    private int quantity;
    private double price;
    private Set<Category> categories = new HashSet<>(); // Set para garantizar unicidad

}
