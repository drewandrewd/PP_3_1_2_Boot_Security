package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>  {
}
