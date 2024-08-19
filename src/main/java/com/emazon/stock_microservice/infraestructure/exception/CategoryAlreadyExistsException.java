package com.emazon.stock_microservice.infraestructure.exception;


// Custom exception made by extending the class of RuntimeException
public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException() {
        super();
    }
}
