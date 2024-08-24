package com.emazon.stock_microservice.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CategoryRequest {


    private Long id;
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @Size(min = 1, max = 90)
    private String description;


    // TODO: poner aca las validaciones con anotaciones @NotBlank etc etc
}
