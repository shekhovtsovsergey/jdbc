package com.shekhovtsov.jdbc.service;

import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll() throws CategoryNotFoundException;
    Category findById(Long id) throws CategoryNotFoundException;
    Optional<?> findNameById(Long id) throws CategoryNotFoundException;
    void insert(Category category) throws CategoryNotFoundException;
    void update(Category category) throws CategoryNotFoundException;
    void deleteById(Long id) throws CategoryNotFoundException;

}
