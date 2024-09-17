package com.emazon.stock_microservice.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArticleStockRequestDTO {

    @NotNull(message = "Article ID cannot be null")
    private String articleName;

    @Min(value = 1, message = "Stock to add must be at least 1")
    private int additionalStock;
}