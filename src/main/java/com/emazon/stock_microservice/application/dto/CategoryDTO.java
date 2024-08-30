package com.emazon.stock_microservice.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @NotBlank(message = "Brand name should not be blank.")
    @Size(min = 1, max = 50)
    private String name;

    @NotBlank(message = "Brand description should not be blank  .")
    @Size(min = 1, max = 90)
    private String description;

}
