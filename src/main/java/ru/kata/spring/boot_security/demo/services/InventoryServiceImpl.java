package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Inventory;
import ru.kata.spring.boot_security.demo.repositories.InventoryRepository;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    public List<Inventory> findAll() {
        return repository.findAll();
    }

    public Inventory save(Inventory inventory) {
        return repository.save(inventory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

