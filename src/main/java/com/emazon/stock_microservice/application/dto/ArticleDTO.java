package com.emazon.stock_microservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String brandName; // agregado en la hu.5 no decia nada de esto pero en la base de datos si entonces que se hace? 👀👀
    private Set<String> categoryNames;
}
