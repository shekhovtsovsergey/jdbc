package com.shekhovtsov.jdbc;

import com.shekhovtsov.jdbc.dao.JdbcUtils;
import com.shekhovtsov.jdbc.dao.JdbcUtilsImpl;
import com.shekhovtsov.jdbc.dao.ProductDao;
import com.shekhovtsov.jdbc.dao.ProductJdbcDao;
import com.shekhovtsov.jdbc.model.Product;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        JdbcUtils jdbcUtils = new JdbcUtilsImpl();
        ProductDao productJdbcDao = new ProductJdbcDao(jdbcUtils);
        DataSource dataSource = jdbcUtils.getDataSource();

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        List<Product> products = productJdbcDao.findAll();
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }
}