package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final UserRepository userRepository;

   @Autowired
   public UserServiceImp(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Transactional
   @Override
   public void delete(User user) {
      userRepository.deleteById(user.getId());
   }

   @Transactional
   @Override
   public void edit(User user) {;
      userRepository.save(user);
   }

   @Override
   @Transactional(readOnly = true)
   public User getUser(Long id) {
      return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
   }

   @Transactional
   @Override
   public void add(User user) {
      userRepository.save(user);
   }

   @Transactional
   @Override
   public List<User> getListUsers() {
      return userRepository.findAll();
   }

   @Transactional
   @Override
   public boolean existsByEmail(String email) {
      return userRepository.findByEmail(email).isPresent();
   }

   @Override
   public User findByEmail(String email) {
      return userRepository.findByEmail(email)
              .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
   }
}
