package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();
    Supplier save(Supplier supplier);
    void delete(Long id);
}

