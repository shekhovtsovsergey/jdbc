INSERT INTO CATEGORIES (NAME) VALUES ('Beer'), ('Wine'), ('Spirits'), ('Cocktails'), ('Non-Alcoholic Drinks');
INSERT INTO PRODUCTS (NAME, CATEGORY_ID, COST, QUANTITY)
VALUES ('Budweiser', 1, 2.5, 100),
       ('Heineken', 1, 3.0, 80),
       ('Corona', 1, 3.5, 70),
       ('Stella Artois', 1, 3.2, 90),
       ('Chardonnay', 2, 25.0, 20),
       ('Merlot', 2, 30.0, 15),
       ('Cabernet Sauvignon', 2, 35.0, 10),
       ('Pinot Noir', 2, 40.0, 5),
       ('Jack Daniel''s', 3, 40.0, 30),
       ('Grey Goose', 3, 45.0, 25),
       ('Jim Beam', 3, 35.0, 20),
       ('Hennessy', 3, 50.0, 15),
       ('Margarita', 4, 8.0, 50),
       ('Mojito', 4, 7.5, 40),
       ('Cosmopolitan', 4, 8.5, 60),
       ('Long Island Iced Tea', 4, 9.0, 30),
       ('Coca-Cola', 5, 2.0, 200),
       ('Sprite', 5, 2.0, 180),
       ('Lemonade', 5, 3.0, 150),
       ('Iced Tea', 5, 3.5, 120);

select * from CATEGORIES;
select * from PRODUCTS;

