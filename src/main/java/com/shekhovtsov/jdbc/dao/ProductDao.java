package com.shekhovtsov.jdbc.dao;


import com.shekhovtsov.jdbc.exception.ProductNotFoundException;
import com.shekhovtsov.jdbc.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDao extends {

    List<Product> findAll() throws ProductNotFoundException, SQLException;
    Optional<Product> findById(Long id) throws ProductNotFoundException;
    Optional<String> findNameById(Long id) throws ProductNotFoundException;
    void insert(Product product) throws ProductNotFoundException;
    void update(Product product) throws ProductNotFoundException;
    @Q
    void deleteById(Long id) throws ProductNotFoundException;

}
