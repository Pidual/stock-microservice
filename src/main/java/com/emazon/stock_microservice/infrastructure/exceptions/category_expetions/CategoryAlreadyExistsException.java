package com.emazon.stock_microservice.infrastructure.exceptions.category_expetions;


// Custom exception made by extending the class of RuntimeException
public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException() {
        super();
    }
}
