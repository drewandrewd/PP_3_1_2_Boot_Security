package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Recipient;
import ru.kata.spring.boot_security.demo.repositories.RecipientRepository;

import java.util.List;

public interface RecipientService {
    List<Recipient> findAll();
    Recipient save(Recipient recipient);
    void delete(Long id);
}
