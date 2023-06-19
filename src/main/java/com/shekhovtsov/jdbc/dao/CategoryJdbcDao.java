package com.shekhovtsov.jdbc.dao;

import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.model.Category;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryJdbcDao implements CategoryDao {

    private JdbcUtils jdbcUtils;

    public CategoryJdbcDao(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }

    //этот код будет один для любого набора сущьностей
    @Override
    public List<Category> findAll() throws CategoryNotFoundException {
        Connection connection = null;
        List<Category> result = new ArrayList<>();
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CATEGORIES");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = Category.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                result.add(category);
            }
            statement.close();

        } catch (SQLException | IOException e) {
            throw new CategoryNotFoundException("Error while finding all categories " + e);
        } finally {
            jdbcUtils.closeConnection(connection);
        }
        return result;
    }

    @Override
    public Optional<Category> findById(Long id) throws CategoryNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CATEGORIES WHERE ID=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(Category.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build());
            }
            statement.close();
        } catch (SQLException | IOException e) {
            throw new CategoryNotFoundException("Error while finding category by id: " + id + e);
        } finally {
            jdbcUtils.closeConnection(connection);
        }
        throw new CategoryNotFoundException("Category with id " + id + " not found.");
    }

    @Override
    public Optional<String> findNameById(Long id) throws CategoryNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT NAME FROM CATEGORIES WHERE ID=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(resultSet.getString("name"));
            } else {
                throw new CategoryNotFoundException("Category not found for id: " + id);
            }
        } catch (SQLException | IOException e) {
            throw new CategoryNotFoundException("Error while finding name by id: " + id + e);
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }

    @Override
    public void insert(Category category) throws CategoryNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CATEGORIES (NAME) VALUES (?)");
            statement.setString(1, category.getName());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            throw new CategoryNotFoundException("Error while inserting category: " + category + e);
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }

    @Override
    public void update(Category category) throws CategoryNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE CATEGORIES SET NAME=? WHERE ID=?");
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated == 0) {
                throw new CategoryNotFoundException("Category with ID " + category.getId() + " not found");
            }
        } catch (SQLException | IOException e) {
            throw new CategoryNotFoundException("Error while update category: " + category + e);
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }

    public void deleteById(Long id) throws CategoryNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM CATEGORIES WHERE ID=?");
            statement.setLong(1, id);
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated == 0) {
                throw new CategoryNotFoundException("Category with ID " + id + " not found");
            }
        } catch (SQLException | IOException e) {
            throw new CategoryNotFoundException("Error while update category: " + id + e);
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }
}
