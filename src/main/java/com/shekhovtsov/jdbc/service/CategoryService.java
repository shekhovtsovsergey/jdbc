package com.shekhovtsov.jdbc.service;

import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category findById(Long id) throws CategoryNotFoundException;
    Category findNameById(Long id) throws CategoryNotFoundException;
    void insert(Category category);
    void update(Category category);
    void deleteById(Long id);

}
