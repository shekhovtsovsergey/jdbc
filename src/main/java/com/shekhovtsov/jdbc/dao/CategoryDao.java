package com.shekhovtsov.jdbc.dao;

import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao <T> {

    List<T> findAll() throws CategoryNotFoundException;
    Optional<T> findById(Long id) throws CategoryNotFoundException;
    Optional<?> findNameById(Long id) throws CategoryNotFoundException;
    void insert(T category) throws CategoryNotFoundException;
    void update(T category) throws CategoryNotFoundException;
    void deleteById(Long id) throws CategoryNotFoundException;

}
