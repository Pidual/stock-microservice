package com.emazon.stock_microservice.domain.exceptions;

public class WrongDescriptionException extends RuntimeException {
    public WrongDescriptionException(String message) {
        super(message);
    }
}
