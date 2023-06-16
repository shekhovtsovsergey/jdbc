package com.shekhovtsov.jdbc.dao;

import com.shekhovtsov.jdbc.dto.ProductDto;
import com.shekhovtsov.jdbc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductDaoDto extends JpaRepository<Product,Long>{

    @Query("SELECT new com.shekhovtsov.jdbc.dto.ProductDto(u.id, u.name, u.category, u.cost, u.quantity) FROM Product u ORDER BY u.name")
    List<ProductDto> findAllProducts();

}
