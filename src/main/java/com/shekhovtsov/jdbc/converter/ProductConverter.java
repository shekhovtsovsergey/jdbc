package com.shekhovtsov.jdbc.converter;


import com.shekhovtsov.jdbc.dto.ProductDto;
import com.shekhovtsov.jdbc.exception.CategoryNotFoundException;
import com.shekhovtsov.jdbc.model.Category;
import com.shekhovtsov.jdbc.model.Product;
import com.shekhovtsov.jdbc.service.CategoryService;


public class ProductConverter {

    private CategoryService categoryService;

    public ProductConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductDto entityToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory().getId())
                .cost(product.getCost())
                .quantity(product.getQuantity())
                .build();
    }

    public Product dtoToEntity(ProductDto productDto) throws CategoryNotFoundException {
        Category category = categoryService.findById(productDto.getCategory());
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .category(category)
                .cost(productDto.getCost())
                .quantity(productDto.getQuantity())
                .build();
    }
}
