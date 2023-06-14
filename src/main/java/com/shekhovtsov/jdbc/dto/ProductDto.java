package com.shekhovtsov.jdbc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@ToString
public class ProductDto {
    private Long id;
    private String name;
    private Long category;
    private BigDecimal cost;
    private int quantity;
}
