package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {

    void createRole(String name);
    void deleteById(Long id);
    List<Role> getAllRoles();
    Role findByName(String name);
    boolean existsByName(String name);
    Role findById(Long id);
}
