package com.shekhovtsov.jdbc;

import com.shekhovtsov.jdbc.config.AppConfig;
import com.shekhovtsov.jdbc.controller.UserCommandsController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws Throwable {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserCommandsController userCommandsController = context.getBean(UserCommandsController.class);
        userCommandsController.play();
        context.close();
    }
}


// +подключить спринг контекст через конфигурационный класс конфиг аннотейшн (сюда)
// +подключить пулл потоков
// -скорректировать по возврату ошибок
// ---проекция спринг дата (реализовать несколько методов которые сразу из БД вытаскивают ДТО)
// написать DAO в виде Generic ()*
//**создать обработчик аннотоаций который по размеченному классу формирует запрос DAO слой должен эти запросы использовать
//**этот код будет один для любого набора сущьностей
//крестики нолики привести в порядок
//настроить аггрегатор логов в микросервисном проекте - перевести проект на DATA - JDBC
//@query и круд репозитори
//подготовить бэклог развития