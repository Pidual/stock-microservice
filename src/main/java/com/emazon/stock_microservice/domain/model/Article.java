package com.emazon.stock_microservice.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Article {

    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private Set<Category> categories = new HashSet<>(); // Set para garantizar unicidad

    public Article(Long id, String name, String description, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    // Métodos para agregar categorías asegurando que no haya duplicados y que el tamaño sea el adecuado
    public void addCategory(Category category) {
        if (categories.size() >= 3) {
            throw new IllegalArgumentException("Un artículo no puede tener más de 3 categorías.");
        }
        categories.add(category);
    }

    public void validate() {
        if (categories.size() < 1) {
            throw new IllegalArgumentException("Un artículo debe tener al menos una categoría.");
        }
        if (categories.size() > 3) {
            throw new IllegalArgumentException("Un artículo no puede tener más de 3 categorías.");
        }
    }

    // Getters y Setters

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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
