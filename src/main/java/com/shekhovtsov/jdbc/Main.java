package com.shekhovtsov.jdbc;

import com.shekhovtsov.jdbc.controller.UserCommands;
import com.shekhovtsov.jdbc.dao.JdbcUtils;
import com.shekhovtsov.jdbc.dao.JdbcUtilsImpl;
import com.shekhovtsov.jdbc.dao.ProductDao;
import com.shekhovtsov.jdbc.dao.ProductJdbcDao;
import com.shekhovtsov.jdbc.service.ProductService;
import com.shekhovtsov.jdbc.service.ProductServiceImpl;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        JdbcUtils jdbcUtils = new JdbcUtilsImpl();
        ProductDao productDao = new ProductJdbcDao(jdbcUtils);
        DataSource dataSource = jdbcUtils.getDataSource();
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
        ProductService productService = new ProductServiceImpl(jdbcUtils, productDao);
        UserCommands userCommands = new UserCommands(productService);
        userCommands.play();

    }
}