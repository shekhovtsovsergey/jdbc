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


// +(готово) подключить спринг контекст через конфигурационный класс конфиг аннотейшн (сюда)
// +(готово)подключить пулл потоков hikari
// -скорректировать по возврату ошибок
// -(решили не делать) проекция спринг дата (реализовать несколько методов которые сразу из БД вытаскивают ДТО)
// +(готово)написать DAO в виде Generic ()*
//**создать обработчик аннотаций который по размеченному классу формирует запрос DAO слой должен эти запросы использовать->
//->этот код будет один для любого набора сущностей
//крестики нолики привести в порядок раскидать по классам
//настроить агрегатор логов в микро сервисном проекте - перевести проект на DATA - JDBC  - @query и CRUD репозитории
//подготовить бэклог развития проекта
//записывать короткие видео по лайв кодингу, чтобы рассуждать на собеседовании