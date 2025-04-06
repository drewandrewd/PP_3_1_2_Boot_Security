package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Category;
import ru.kata.spring.boot_security.demo.models.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
