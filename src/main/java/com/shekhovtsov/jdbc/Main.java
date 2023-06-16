package com.shekhovtsov.jdbc;

import com.shekhovtsov.jdbc.config.AppConfig;
import com.shekhovtsov.jdbc.controller.UserCommandsController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws Throwable {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        UserCommandsController userCommandsController = context.getBean(UserCommandsController.class);
        userCommandsController.play();
    }
}


// подключить спринг контекст через конфигурационный класс конфиг аннотейшн (сюда)
// подключить пулл потоков
// скорректировать по возврату ошибок
// проекция спринг дата (реализовать несколько методов которые сразу из БД вытаскивают ДТО)
// написать DAO в виде Generic ()*



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



//Прошлая версия
//        JdbcUtils jdbcUtils = new JdbcUtilsImpl();
//        ConnectionManager connectionManager = new ConnectionManagerImpl();
//        ConnectionManagerImpl.getInstance().initialize();
//        ProductDao productDao = new ProductJdbcDao(jdbcUtils,connectionManager);
//        CategoryDao categoryDao = new CategoryJdbcDao(jdbcUtils);
//        DataSource dataSource = jdbcUtils.getDataSource();
//
//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//        flyway.migrate();
//
//        CategoryService categoryService = new CategoryServiceImpl(categoryDao);
//        ProductConverter productConverter = new ProductConverter(categoryService);
//        CategoryConverter categoryConverter = new CategoryConverter();
//        ProductService productService = new ProductServiceImpl(productDao, categoryService, productConverter);
//        UserCommandsController userCommandsController = new UserCommandsController(productService);
//        userCommandsController.play();