package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Supplier;
import ru.kata.spring.boot_security.demo.repositories.SupplierRepository;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;

    public SupplierServiceImpl(SupplierRepository repository) {
        this.repository = repository;
    }

    public List<Supplier> findAll() {
        return repository.findAll();
    }

    public Supplier save(Supplier supplier) {
        return repository.save(supplier);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
