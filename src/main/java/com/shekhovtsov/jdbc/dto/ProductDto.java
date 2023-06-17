package com.shekhovtsov.jdbc.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Long category;
    private BigDecimal cost;
    private int quantity;
}
