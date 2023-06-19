package com.shekhovtsov.jdbc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CategoryDto {
    private Long id;
    private String name;
}
