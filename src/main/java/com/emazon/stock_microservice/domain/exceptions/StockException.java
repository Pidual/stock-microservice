package com.emazon.stock_microservice.domain.exceptions;

public class StockException extends RuntimeException {
    public StockException(String message) {
        super(message);
    }
}
