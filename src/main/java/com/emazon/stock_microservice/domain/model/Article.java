package com.emazon.stock_microservice.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Article {

    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private Brand brand;
    private Set<Category> categories = new HashSet<>(); // Set para garantizar unicidad

    public Article(Long id, double price, int quantity, String description, String name,Brand brand, Set<Category> categories) {
        this.id = id;
        this.categories = categories;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
