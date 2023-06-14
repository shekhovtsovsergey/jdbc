package com.shekhovtsov.jdbc.service;

import com.shekhovtsov.jdbc.dto.ProductDto;
import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.exception.ProductNotFoundException;
import com.shekhovtsov.jdbc.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll() throws ProductNotFoundException;
    Optional<Product> findById(Long id) throws ProductNotFoundException;
    Optional<String> findNameById(Long id) throws ProductNotFoundException;
    void insert(ProductDto productDto) throws CategoryNotFoundException;
    void update(Product product);
    void deleteById(Long id);
}
