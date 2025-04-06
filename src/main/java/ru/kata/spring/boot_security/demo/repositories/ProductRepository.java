package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Product;

public interface ProductRepository  extends JpaRepository<Product, Long> {
}
