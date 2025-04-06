package ru.kata.spring.boot_security.demo.services;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Location;
import ru.kata.spring.boot_security.demo.repositories.LocationRepository;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;

    public LocationServiceImpl(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> findAll() {
        return repository.findAll();
    }

    public Location save(Location location) {
        return repository.save(location);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
