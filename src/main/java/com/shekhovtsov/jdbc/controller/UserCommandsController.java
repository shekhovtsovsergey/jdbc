package com.shekhovtsov.jdbc.controller;

import com.shekhovtsov.jdbc.dto.ProductDto;
import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.exception.ProductNotFoundException;
import com.shekhovtsov.jdbc.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserCommandsController {

    private ProductService productService;

    public UserCommandsController(ProductService productService) {
        this.productService = productService;
    }

    public void play() throws ProductNotFoundException, CategoryNotFoundException {
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
                    List<ProductDto> products = productService.findAll();
                    for (ProductDto p : products) {
                        System.out.println(p);
                    }
                    break;
                case "2":
                    System.out.println("Товар по ID: ");
                    ProductDto productById = productService.findById(1L);
                    System.out.println(productById);
                    break;
                case "3":
                    System.out.println("Наименование товара по ID: ");
                    Optional<String> productNameById = productService.findNameById(1L);
                    System.out.println(productNameById);
                    break;
                case "4":
                    System.out.println("Добавление нового товара: ");
                    System.out.println("Введите название товара: ");
                    String name = scanner.nextLine();
                    System.out.println("Введите категорию товара (ID): ");
                    Long category = scanner.nextLong();
                    System.out.println("Введите стоимость товара: ");
                    BigDecimal cost = scanner.nextBigDecimal();
                    System.out.println("Введите количество товара: ");
                    int quantity = scanner.nextInt();
                    ProductDto newProductDto = ProductDto.builder()
                            .name(name)
                            .category(category)
                            .cost(cost)
                            .quantity(quantity)
                            .build();
                    productService.insert(newProductDto);
                    System.out.println("Добавлен");
                    scanner.nextLine();
                    break;
                case "5":
                    System.out.println("Обновление товара: ");
                    System.out.println("Введите ID товара, который нужно обновить: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    ProductDto updatedProductDto = ProductDto.builder()
                            .id(id)
                            .name("")
                            .category(1L)
                            .cost(BigDecimal.ZERO)
                            .quantity(0)
                            .build();
                    System.out.println("Введите новое название товара: ");
                    updatedProductDto.setName(scanner.nextLine());
                    System.out.println("Введите новую цену товара: ");
                    updatedProductDto.setCost(scanner.nextBigDecimal());
                    System.out.println("Введите новое количество товара: ");
                    updatedProductDto.setQuantity(scanner.nextInt());
                    productService.update(updatedProductDto);
                    System.out.println("Обновлен");
                    scanner.nextLine();
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
