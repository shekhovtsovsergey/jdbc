package com.shekhovtsov.jdbc.dto;

import com.shekhovtsov.jdbc.model.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private Long category;
    private BigDecimal cost;
    private int quantity;
}
