package com.shekhovtsov.jdbc.service;

import com.shekhovtsov.jdbc.dao.CategoryDao;
import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.model.Category;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() throws CategoryNotFoundException {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) throws Throwable {
        return (Category) categoryDao.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Optional<Category> findNameById(Long id) throws Throwable {
        return (Optional<Category>) categoryDao.findNameById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public void insert(Category category) throws CategoryNotFoundException {
        categoryDao.insert(category);
    }

    @Override
    public void update(Category category) throws CategoryNotFoundException {
        categoryDao.update(category);
    }

    @Override
    public void deleteById(Long id) throws CategoryNotFoundException {
        categoryDao.deleteById(id);
    }
}
