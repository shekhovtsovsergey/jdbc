package com.shekhovtsov.jdbc.converter;


import com.shekhovtsov.jdbc.dto.CategoryDto;
import com.shekhovtsov.jdbc.model.Category;


public class CategoryConverter {

    public CategoryDto entityToDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryDto;
    }
}
