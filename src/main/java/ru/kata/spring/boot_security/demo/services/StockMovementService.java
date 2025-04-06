package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.StockMovement;

import java.util.List;

public interface StockMovementService {
    List<StockMovement> findAll();
    StockMovement save(StockMovement movement);
    void delete(Long id);
}
