package com.shekhovtsov.jdbc.service;

import com.shekhovtsov.jdbc.converter.ProductConverter;
import com.shekhovtsov.jdbc.dao.ProductDao;
import com.shekhovtsov.jdbc.dto.ProductDto;
import com.shekhovtsov.jdbc.exception.ProductNotFoundException;
import com.shekhovtsov.jdbc.model.Category;
import com.shekhovtsov.jdbc.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private CategoryService categoryService;
    private ProductConverter productConverter;


    public ProductServiceImpl(ProductDao productDao, CategoryService categoryService, ProductConverter productConverter) {
        this.productDao = productDao;
        this.categoryService = categoryService;
        this.productConverter = productConverter;
    }


    @Override
    public List<ProductDto> findAll() throws ProductNotFoundException {
        List<Product> productList = productDao.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            productDtoList.add(productConverter.entityToDto(productList.get(i)));
        }
        return productDtoList;
    }


    @Override
    public ProductDto findById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productDao.findById(id);
        if(product.isPresent()) {
            return productConverter.entityToDto(product.get());
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public Optional<String> findNameById(Long id) throws ProductNotFoundException {
        String name = String.valueOf(productDao.findNameById(id));
        if(name != null) {
            return Optional.of(name);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public void insert(ProductDto productDto) throws Throwable {
        Category category = categoryService.findById(productDto.getCategory());
        productDao.insert(
                Product.builder()
                        .id(productDto.getId())
                        .name(productDto.getName())
                        .category(category)
                        .cost(productDto.getCost())
                        .quantity(productDto.getQuantity())
                .build());
    }

    @Override
    public void update(ProductDto productDto) throws Throwable {
        Product product = productConverter.dtoToEntity(productDto);
        productDao.update(product);
    }

    @Override
    public void deleteById(Long id) throws ProductNotFoundException {
        productDao.deleteById(id);
    }
}
