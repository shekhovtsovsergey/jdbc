package com.shekhovtsov.jdbc.dao;


import com.shekhovtsov.jdbc.exception.ProductNotFoundException;
import com.shekhovtsov.jdbc.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> findAll() throws ProductNotFoundException;
    Optional<Product> findById(Long id) throws ProductNotFoundException;
    Optional<String> findNameById(Long id) throws ProductNotFoundException;
    void insert(Product product) throws ProductNotFoundException;
    void update(Product product) throws ProductNotFoundException;
    void deleteById(Long id) throws ProductNotFoundException;

}
