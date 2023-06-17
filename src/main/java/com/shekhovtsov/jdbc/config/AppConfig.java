package com.shekhovtsov.jdbc.config;


import com.shekhovtsov.jdbc.controller.UserCommandsController;
import com.shekhovtsov.jdbc.converter.CategoryConverter;
import com.shekhovtsov.jdbc.converter.ProductConverter;
import com.shekhovtsov.jdbc.dao.*;
import com.shekhovtsov.jdbc.dao.connection.ConnectionManager;
import com.shekhovtsov.jdbc.dao.connection.ConnectionManagerImpl;
import com.shekhovtsov.jdbc.dao.hikari.ConnectionManagerHikari;
import com.shekhovtsov.jdbc.dao.hikari.ConnectionManagerHikariImpl;
import com.shekhovtsov.jdbc.service.CategoryService;
import com.shekhovtsov.jdbc.service.CategoryServiceImpl;
import com.shekhovtsov.jdbc.service.ProductService;
import com.shekhovtsov.jdbc.service.ProductServiceImpl;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
public class AppConfig {

    @Bean
    public ConnectionManagerHikari connectionManagerHikari() {
        ConnectionManagerHikariImpl hikariImpl = new ConnectionManagerHikariImpl();
        hikariImpl.initialize();
        return hikariImpl;
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl(productDao(), categoryService(), productConverter());
    }

    @Bean
    public JdbcUtils jdbcUtils() {
        return new JdbcUtilsImpl();
    }

    @Bean
    public ConnectionManager connectionManager() {
        ConnectionManagerImpl.getInstance().initialize();
        return ConnectionManagerImpl.getInstance();
    }


    @Bean
    public ProductDao productDao() {
        return new ProductJdbcDao(jdbcUtils(), connectionManager());
    }

    @Bean
    public CategoryDao categoryDao() {
        return new CategoryJdbcDao(jdbcUtils());
    }

    @Bean
    public DataSource dataSource() throws IOException {
        return jdbcUtils().getDataSource();
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() throws IOException {
        return Flyway.configure().dataSource(dataSource()).load();
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryDao());
    }

    @Bean
    public ProductConverter productConverter() {
        return new ProductConverter(categoryService());
    }

    @Bean
    public CategoryConverter categoryConverter() {
        return new CategoryConverter();
    }


    @Bean
    public UserCommandsController userCommandsController() {
        return new UserCommandsController(productService());
    }
}
