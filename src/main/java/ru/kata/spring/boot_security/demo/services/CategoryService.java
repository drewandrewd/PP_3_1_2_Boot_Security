package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    void delete(Long id);
}
