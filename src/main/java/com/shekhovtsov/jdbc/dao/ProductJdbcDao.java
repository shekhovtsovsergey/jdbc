package com.shekhovtsov.jdbc.dao;

import com.shekhovtsov.jdbc.exception.ProductNotFoundException;
import com.shekhovtsov.jdbc.model.Category;
import com.shekhovtsov.jdbc.model.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProductJdbcDao implements ProductDao {

    private JdbcUtils jdbcUtils;

    public ProductJdbcDao(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }

    @Override
    public List<Product> findAll() throws ProductNotFoundException {
        Connection connection = null;
        List<Product> result = new ArrayList<>();
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT p.*, c.NAME as CATEGORY_NAME, c.ID as CATEGORY_ID FROM PRODUCTS p LEFT JOIN CATEGORIES c ON p.CATEGORY_ID = c.ID");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = Category.builder()
                        .id(resultSet.getLong("CATEGORY_ID"))
                        .name(resultSet.getString("CATEGORY_NAME"))
                        .build();
                Product product = Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .category(category != null && category.getId() != null ? category : null)
                        .cost(resultSet.getBigDecimal("cost"))
                        .quantity(resultSet.getInt("quantity"))
                        .build();
                result.add(product);
            }
            statement.close();

        } catch (SQLException | IOException e) {
            throw new ProductNotFoundException("Failed to find products" + e);
        } finally {
            jdbcUtils.closeConnection(connection);
        }
        if (result.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return result;
    }

    @Override
    public Optional<Product> findById(Long id) throws ProductNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT p.*, c.NAME as CATEGORY_NAME, c.ID as CATEGORY_ID FROM PRODUCTS p JOIN CATEGORIES c ON p.CATEGORY_ID = c.ID WHERE p.ID=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = Category.builder()
                        .id(resultSet.getLong("category_id"))
                        .name(resultSet.getString("category_name"))
                        .build();
                return Optional.of(Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .category(category)
                        .cost(resultSet.getBigDecimal("cost"))
                        .quantity(resultSet.getInt("quantity"))
                        .build());
            }
            statement.close();
        } catch (SQLException | IOException e) {
            throw new ProductNotFoundException("Failed to find product with id = " + id + e);
        } finally {
            jdbcUtils.closeConnection(connection);
        }
        return Optional.empty();
    }


    @Override
    public Optional<String> findNameById(Long id) throws ProductNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT name FROM PRODUCTS WHERE ID=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getString("name"));
            }
            statement.close();
        } catch (SQLException | IOException e) {
            throw new ProductNotFoundException("Failed to find product name with id = " + id + e);
        } finally {
            jdbcUtils.closeConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public void insert(Product product) throws ProductNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCTS (NAME,CATEGORY_ID,COST,QUANTITY) VALUES (?,?,?,?)");
            statement.setString(1, product.getName());
            statement.setLong(2, product.getCategory().getId());
            statement.setBigDecimal(3, product.getCost());
            statement.setInt(4, product.getQuantity());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            throw new ProductNotFoundException("Error while inserting product: " + e.getMessage());
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }

    @Override
    public void update(Product product) throws ProductNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE PRODUCTS SET NAME=?, CATEGORY_ID=?, COST=?, QUANTITY=? WHERE ID=?");
            statement.setString(1, product.getName());
            statement.setLong(2, product.getCategory().getId());
            statement.setBigDecimal(3, product.getCost());
            statement.setInt(4, product.getQuantity());
            statement.setLong(5, product.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new ProductNotFoundException("Product not found!");
            }

            statement.close();
        } catch (SQLException | IOException e) {
            throw new ProductNotFoundException("Error while updating product: " + e.getMessage());
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) throws ProductNotFoundException {
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODUCTS WHERE ID=?");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            throw new ProductNotFoundException("Error while deleting product: " + e.getMessage());
        } finally {
            jdbcUtils.closeConnection(connection);
        }
    }
}
