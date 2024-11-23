package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   @Override
   <S extends User> S save(S entity);

   @Override
   List<User> findAll();

   @Override
   void deleteById(Long aLong);

   @Override
   Optional<User> findById(Long aLong);

   Optional<User> findByEmail(String email);
}
