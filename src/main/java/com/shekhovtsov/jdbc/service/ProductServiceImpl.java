package com.shekhovtsov.jdbc.service;

import com.shekhovtsov.jdbc.dao.ProductDao;
import com.shekhovtsov.jdbc.dto.ProductDto;
import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.exception.ProductNotFoundException;
import com.shekhovtsov.jdbc.model.Category;
import com.shekhovtsov.jdbc.model.Product;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService{

    private ProductDao productJdbcDao;
    private CategoryService categoryService;

    public ProductServiceImpl(ProductDao productJdbcDao) {
        this.productJdbcDao = productJdbcDao;
    }

    @Override
    public List<Product> findAll() throws ProductNotFoundException {
        return productJdbcDao.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) throws ProductNotFoundException {
        return productJdbcDao.findById(id);
    }

    @Override
    public Optional<String> findNameById(Long id) throws ProductNotFoundException {
        return productJdbcDao.findNameById(id);
    }

    @Override
    public void insert(ProductDto productDto) throws CategoryNotFoundException {
        Category category = categoryService.findById(productDto.getCategory());


        productJdbcDao.insert(new Product(null,productDto.getName(),category,productDto.getCost(), productDto.getQuantity()));
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
