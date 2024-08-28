package com.emazon.stock_microservice.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // this was added for testing purposes
public class BrandDTO {

    // Constructors, getters, setters, and validation annotations

    @NotBlank
    @NotEmpty(message = "Brand name should not be empty.")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @NotEmpty(message = "Brand description should not be empty.")
    @Size(min = 2, max = 120)
    private String description;
}
