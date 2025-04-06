package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Location;

import java.util.List;

public interface LocationService {
    List<Location> findAll();
    Location save(Location location);
    void delete(Long id);
}
