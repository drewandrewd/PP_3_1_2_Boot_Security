package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
}
