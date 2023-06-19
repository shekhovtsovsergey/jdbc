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

    public void play() throws Throwable {
        System.out.println("Здравствуйте, добро пожаловать в интернет магазин");
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (!command.equals("q")) {
            System.out.println("Введите команду (введите 0 для обзора всех команд):");
            command = scanner.nextLine();
            try {
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
                        showAllProducts();
                        break;
                    case "2":
                        showProductById();
                        break;
                    case "3":
                        showProductNameById();
                        break;
                    case "4":
                        addNewProduct();
                        break;
                    case "5":
                        updateProduct();
                        break;
                    case "6":
                        deleteProduct();
                        break;
                    default:
                        System.out.println("Неверная команда. Введите 0 для обзора всех команд");
                        break;
                }
            } catch (ProductNotFoundException e) {
                System.out.println("Product not found!" + e);
            } catch (CategoryNotFoundException e) {
                System.out.println("Category not found!" + e);
            }
        }
    }

        private void showAllProducts () throws ProductNotFoundException {
            System.out.println("Список всех товаров: ");
            List<ProductDto> products = productService.findAll();
            for (ProductDto p : products) {
                System.out.println(p);
            }
        }

        private void showProductById () throws ProductNotFoundException {
            System.out.println("Товар по ID: ");
            System.out.println("Введите ID товара:");
            Scanner scanner = new Scanner(System.in);
            Long id = scanner.nextLong();
            ProductDto productById = productService.findById(id);
            System.out.println(productById);
        }

        private void showProductNameById () throws ProductNotFoundException {
            System.out.println("Наименование товара по ID: ");
            System.out.println("Введите ID товара:");
            Scanner scanner = new Scanner(System.in);
            Long id = scanner.nextLong();
            Optional<String> productName = productService.findNameById(id);
            System.out.println(productName);
        }

        private void addNewProduct () throws Throwable {
            System.out.println("Добавление нового товара: ");
            System.out.println("Введите название товара: ");
            Scanner scanner = new Scanner(System.in);
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
        }

        private void updateProduct () throws Throwable {
            System.out.println("Обновление товара");
            System.out.println("Введите ID товара, который хотите обновить:");
            Scanner scanner = new Scanner(System.in);
            Long id = scanner.nextLong();
            scanner.nextLine();
            System.out.println("Введите новое наименование товара:");
            String name = scanner.nextLine();
            System.out.println("Введите новую цену товара:");
            BigDecimal price = scanner.nextBigDecimal();
            System.out.println("Введите новый ID категории:");
            Long categoryId = scanner.nextLong();
            System.out.println("Введите новое количество товара:");
            int quantity = scanner.nextInt();
            ProductDto updatedProduct = ProductDto.builder()
                    .id(id)
                    .name(name)
                    .cost(price)
                    .category(categoryId)
                    .quantity(quantity)
                    .build();
            productService.update(updatedProduct);
            System.out.println("Товар успешно обновлен");
        }

        private void deleteProduct () throws ProductNotFoundException {
            System.out.println("Удаление товара");
            System.out.println("Введите ID товара, который хотите удалить:");
            Scanner scanner = new Scanner(System.in);
            Long id = scanner.nextLong();
            productService.deleteById(id);
            System.out.println("Товар успешно удален");
        }
    }
