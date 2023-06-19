package com.shekhovtsov.jdbc.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class Category {
    private Long id;
    private String name;

}
