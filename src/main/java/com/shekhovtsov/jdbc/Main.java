package com.shekhovtsov.jdbc;

import com.shekhovtsov.jdbc.controller.UserCommandsController;
import com.shekhovtsov.jdbc.converter.CategoryConverter;
import com.shekhovtsov.jdbc.converter.ProductConverter;
import com.shekhovtsov.jdbc.dao.*;
import com.shekhovtsov.jdbc.service.CategoryService;
import com.shekhovtsov.jdbc.service.CategoryServiceImpl;
import com.shekhovtsov.jdbc.service.ProductService;
import com.shekhovtsov.jdbc.service.ProductServiceImpl;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        float a = 0.7f; // 32 бита
//        float b = 0.0f; // 64 бита
//
//        for (int i = 0; i < 70; i++) {
//            b += 0.01f; // подучить
//        }
//        System.out.println(a==b);
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(Math.abs(a-b)<0.00001);
        //почитать кто использует флоат


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
    }
}


// подключить спринг контекст через конфигурационный класс конфиг аннотейшн (сюда)
// подключить пулл потоков
// скорректировать по возврату ошибок
// проекция спринг дата (реализовать несколько методов которые сразу из БД вытаскивают ДТО)
// написать DAO в виде Generic ()*
