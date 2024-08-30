package com.emazon.stock_microservice.domain.exceptions;

public class WrongNameException extends RuntimeException {
    public WrongNameException(String message) {
        super(message);
    }
}
