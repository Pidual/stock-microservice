package com.emazon.stock_microservice.domain.model;

public class Brand {

    private Long id;
    private String name;
    private String description;

    // Constructors, getters, setters, and business logic methods

    public Brand(Long id, String name,String description) {
        this.id = id;
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
}
