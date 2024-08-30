package com.emazon.stock_microservice.domain.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Article {

    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private Set<Category> categories = new HashSet<>();


    public Article(Long id, String name, String description, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }




}

