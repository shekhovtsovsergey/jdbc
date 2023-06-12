package com.shekhovtsov.jdbc.dao;

import com.shekhovtsov.jdbc.model.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProductJdbcDao implements ProductDao {

    private JdbcUtils jdbcUtils;


    public ProductJdbcDao(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }

    @Override
    public List<Product> findAll() {
        Connection connection = null;

        Set<Product> result = new HashSet<>();

        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Product product = Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                result.add(product);
            }
            statement.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeConnection(connection);
        }
        return new ArrayList<>(result);
    }


    @Override
    public Product findById(Long id) {
        Connection connection = null;

        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE ID=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
            }
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public String findNameById(Long id) {
        Connection connection = null;

        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT NAME FROM PRODUCTS WHERE ID=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void insert(Product product) {
        Connection connection = null;

        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCTS (NAME) VALUES (?)");
            statement.setString(1, product.getName());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }

    @Override
    public void update(Product product) {
        Connection connection = null;

        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE PRODUCTS SET NAME=? WHERE ID=?");
            statement.setString(1, product.getName());
            statement.setLong(2, product.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = null;

        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODUCTS WHERE ID=?");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }
}
