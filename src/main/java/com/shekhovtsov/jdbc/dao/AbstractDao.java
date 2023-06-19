package com.shekhovtsov.jdbc.dao;

import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public interface AbstractDao<ID,T> {

    List<T> findAll();

    Optional<T> findById(ID id);

    void insert(T entity);

    void update(T entity);

    void deleteById(ID id);


}
