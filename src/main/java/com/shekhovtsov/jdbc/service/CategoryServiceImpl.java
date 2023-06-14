package com.shekhovtsov.jdbc.service;

import com.shekhovtsov.jdbc.dao.CategoryDao;
import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) throws CategoryNotFoundException {
        return categoryDao.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category findNameById(Long id) throws CategoryNotFoundException {
        return categoryDao.findNameById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryDao.deleteById(id);
    }
}
