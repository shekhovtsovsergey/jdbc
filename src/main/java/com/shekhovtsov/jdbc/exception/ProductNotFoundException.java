package com.shekhovtsov.jdbc.exception;

public class ProductNotFoundException extends ObjectNotFoundException {

    public ProductNotFoundException(Long id) {
        super(String.format("Product id %s not found", id));
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
