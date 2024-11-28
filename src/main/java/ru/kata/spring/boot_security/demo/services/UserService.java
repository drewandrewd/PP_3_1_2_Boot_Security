package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> getListUsers();
    void delete(Long id);
    User edit(User user);
    User getUser(Long id);
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
