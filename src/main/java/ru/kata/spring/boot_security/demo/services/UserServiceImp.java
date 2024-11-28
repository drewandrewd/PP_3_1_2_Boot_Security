package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @Transactional
   @Override
   public void delete(Long id) {
      userRepository.deleteById(id);
   }

   @Transactional
   @Override
   public User edit(User user) {;
      encodePassword(user);
      user.setRoles(assignRoles(user.getRoles()));
      userRepository.save(user);
      return user;
   }

   @Override
   @Transactional(readOnly = true)
   public User getUser(Long id) {
      return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
   }

   @Transactional
   @Override
   public void add(User user) {
      encodePassword(user);
      user.setRoles(assignRoles(user.getRoles()));
      userRepository.save(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getListUsers() {
      return userRepository.findAll();
   }

   @Transactional(readOnly = true)
   @Override
   public boolean existsByEmail(String email) {
      return userRepository.findByEmail(email).isPresent();
   }

   @Transactional(readOnly = true)
   @Override
   public User findByEmail(String email) {
      return userRepository.findByEmail(email)
              .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
   }

   private void encodePassword(User user) {
      if (user.getPassword() != null && !user.getPassword().isEmpty()) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
      }
   }

   private Set<Role> assignRoles(Set<Role> roles) {
      return roles.stream()
              .map(role -> roleRepository.findByName(role.getName()).get())
              .collect(Collectors.toSet());
   }
}
