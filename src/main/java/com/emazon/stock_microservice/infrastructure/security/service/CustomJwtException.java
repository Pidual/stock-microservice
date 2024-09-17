package com.emazon.stock_microservice.infrastructure.security.service;

public class CustomJwtException extends RuntimeException {
    public CustomJwtException(String message) {
        super(message);
    }
}
