package com.emazon.stock_microservice.infrastructure.exceptions.category_expetions;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(String message) {
        super(message);
    }
}
