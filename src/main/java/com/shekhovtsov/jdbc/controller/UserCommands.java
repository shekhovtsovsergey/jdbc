package com.shekhovtsov.jdbc.controller;

import com.shekhovtsov.jdbc.model.Category;
import com.shekhovtsov.jdbc.model.Product;
import com.shekhovtsov.jdbc.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class UserCommands {

    private ProductService productService;

    public UserCommands(ProductService productService) {
        this.productService = productService;
    }

    public void play() {
        Category category = Category.builder()
                .id(1l)
                .name("Новая")
                .build();

        Product product = Product.builder()
                .name("Product")
                .category(category)
                .cost(BigDecimal.valueOf(100))
                .quantity(100)
                .build();

        Product productFromDb = Product.builder()
                .id(1L)
                .name("Product")
                .category(category)
                .cost(BigDecimal.valueOf(100))
                .quantity(100)
                .build();

        System.out.println("Здравствуйте, добро пожаловать в интернет магазин");
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (!command.equals("q")) {
            System.out.println("Введите команду (введите 0 для обзора всех команд):");
            command = scanner.nextLine();

            switch (command) {
                case "0":
                    System.out.println("Список всех команд:");
                    System.out.println("1 - Список всех товаров");
                    System.out.println("2 - Товар по ID");
                    System.out.println("3 - Наименование товара по ID");
                    System.out.println("4 - Добавление нового товара");
                    System.out.println("5 - Обновление товара");
                    System.out.println("6 - Удаление товара");
                    System.out.println("q - Выход");
                    break;
                case "1":
                    System.out.println("Список всех товаров: ");
                    List<Product> products = productService.findAll();
                    for (Product p : products) {
                        System.out.println(p);
                    }
                    break;
                case "2":
                    System.out.println("Товар по ID: ");
                    Product productById = productService.findById(1L);
                    System.out.println(productById);
                    break;
                case "3":
                    System.out.println("Наименование товара по ID: ");
                    String productNameById = productService.findNameById(1L);
                    System.out.println(productNameById);
                    break;
                case "4":
                    System.out.println("Добавление нового товара: ");
                    productService.insert(product);
                    System.out.println("Добавлен");
                    break;
                case "5":
                    System.out.println("Обновление товара: ");
                    productService.update(productFromDb);
                    System.out.println("Обновлен");
                    break;
                case "6":
                    System.out.println("Удаление товара: ");
                    productService.deleteById(1L);
                    System.out.println("Удален");
                    break;
                case "q":
                    System.out.println("Работа приложения завершена.");
                    break;
                default:
                    System.out.println("Неправильная команда, попробуйте снова!");
                    break;
            }
        }
    }
}
