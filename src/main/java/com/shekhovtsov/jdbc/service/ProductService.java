package com.shekhovtsov.jdbc.service;

import com.shekhovtsov.jdbc.dto.ProductDto;
import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.exception.ProductNotFoundException;
import com.shekhovtsov.jdbc.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findAllProducts() throws ProductNotFoundException;
    List<ProductDto> findAll() throws ProductNotFoundException;
    ProductDto findById(Long id) throws ProductNotFoundException;
    Optional<String> findNameById(Long id) throws ProductNotFoundException;
    void insert(ProductDto productDto) throws Throwable;
    void update(ProductDto productDto) throws Throwable;
    void deleteById(Long id) throws ProductNotFoundException;
}
