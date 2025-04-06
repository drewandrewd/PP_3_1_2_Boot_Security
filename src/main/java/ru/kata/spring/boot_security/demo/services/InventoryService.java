package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> findAll();
    Inventory save(Inventory inventory);
    void delete(Long id);
}
