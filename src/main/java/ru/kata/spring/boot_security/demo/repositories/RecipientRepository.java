package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {
}
