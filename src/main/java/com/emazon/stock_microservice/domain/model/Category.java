package com.emazon.stock_microservice.domain.model;

// No utilizamos lombok por que estamos haciendolo purista
// Pero pues depende de uno
// Ademas yo creo que la proxima vez lo haga con lombok
// Esta sin lombok por el tema de que el dominio no tiene que tener cosas de afuera solo el lenguaje de programacion

public class Category {

    private Long id;
    private String name;
    private String description;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
