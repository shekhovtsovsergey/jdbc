package com.shekhovtsov.jdbc.dao;

import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao extends AbstractDao<Long,Category> {

    Optional<Category> findNameById(Long id);

}
