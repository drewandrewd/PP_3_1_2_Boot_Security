package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get();
        System.out.println(user.getPassword());
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email не найден: " + email));
    }
}
