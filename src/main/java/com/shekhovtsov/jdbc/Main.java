package com.shekhovtsov.jdbc;

import com.shekhovtsov.jdbc.controller.UserCommandsController;
import com.shekhovtsov.jdbc.converter.CategoryConverter;
import com.shekhovtsov.jdbc.converter.ProductConverter;
import com.shekhovtsov.jdbc.dao.*;
import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.exception.ProductNotFoundException;
import com.shekhovtsov.jdbc.service.CategoryService;
import com.shekhovtsov.jdbc.service.CategoryServiceImpl;
import com.shekhovtsov.jdbc.service.ProductService;
import com.shekhovtsov.jdbc.service.ProductServiceImpl;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        JdbcUtils jdbcUtils = new JdbcUtilsImpl();
        ProductDao productDao = new ProductJdbcDao(jdbcUtils);
        CategoryDao categoryDao = new CategoryJdbcDao(jdbcUtils);
        DataSource dataSource = jdbcUtils.getDataSource();

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        CategoryService categoryService = new CategoryServiceImpl(categoryDao);
        ProductConverter productConverter = new ProductConverter(categoryService);
        CategoryConverter categoryConverter = new CategoryConverter();
        ProductService productService = new ProductServiceImpl(productDao, categoryService, productConverter);
        UserCommandsController userCommandsController = new UserCommandsController(productService);
        userCommandsController.play();

        /*try {
            userCommandsController.play();
        } catch (ProductNotFoundException e) {
            System.out.println("Product not found!" + e);
        } catch (CategoryNotFoundException e) {
            System.out.println("Product not found!" + e);
        }*/
    }
}