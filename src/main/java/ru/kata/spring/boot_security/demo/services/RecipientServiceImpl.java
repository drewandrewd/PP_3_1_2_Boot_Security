package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Recipient;
import ru.kata.spring.boot_security.demo.repositories.RecipientRepository;

import java.util.List;

@Service
public class RecipientServiceImpl implements RecipientService {

    private final RecipientRepository repository;

    public RecipientServiceImpl(RecipientRepository repository) {
        this.repository = repository;
    }

    public List<Recipient> findAll() {
        return repository.findAll();
    }

    public Recipient save(Recipient recipient) {
        return repository.save(recipient);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
