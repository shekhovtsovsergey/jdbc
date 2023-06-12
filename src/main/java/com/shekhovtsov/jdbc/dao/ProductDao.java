package com.shekhovtsov.jdbc.dao;


import com.shekhovtsov.jdbc.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();
    Product findById(Long id);
    String findNameById(Long id);
    void insert(Product product);
    void update(Product product);
    void deleteById(Long id);

}
