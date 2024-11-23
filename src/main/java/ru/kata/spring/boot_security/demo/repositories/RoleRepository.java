package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    List<Role> findAll();

    @Override
    <S extends Role> S save(S entity);

    @Override
    void deleteById(Long aLong);

    Optional<Role> findByName(String name);
}
