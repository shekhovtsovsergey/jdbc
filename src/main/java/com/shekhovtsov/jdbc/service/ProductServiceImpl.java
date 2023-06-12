package com.shekhovtsov.jdbc.service;

import com.shekhovtsov.jdbc.dao.JdbcUtils;
import com.shekhovtsov.jdbc.dao.ProductDao;
import com.shekhovtsov.jdbc.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService{

    private JdbcUtils jdbcUtils;
    private ProductDao productJdbcDao;

    public ProductServiceImpl(JdbcUtils jdbcUtils, ProductDao productJdbcDao) {
        this.jdbcUtils = jdbcUtils;
        this.productJdbcDao = productJdbcDao;
    }

    @Override
    public List<Product> findAll() {
        return productJdbcDao.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productJdbcDao.findById(id);
    }

    @Override
    public String findNameById(Long id) {
        return productJdbcDao.findNameById(id);
    }

    @Override
    public void insert(Product product) {
        productJdbcDao.insert(product);
    }

    @Override
    public void update(Product product) {
        productJdbcDao.update(product);
    }

    @Override
    public void deleteById(Long id) {
        productJdbcDao.deleteById(id);
    }
}
