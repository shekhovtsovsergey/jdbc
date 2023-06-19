package com.shekhovtsov.jdbc.exception;

public class CategoryNotFoundException extends ObjectNotFoundException {

    public CategoryNotFoundException(Long id) {
        super(String.format("Category id %s not found", id));
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
