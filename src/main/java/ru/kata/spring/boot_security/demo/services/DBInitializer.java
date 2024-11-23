package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.configs.WebSecurityConfig;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DBInitializer {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBInitializer(UserService userService, RoleService roleService, WebSecurityConfig webSecurityConfig, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (!roleService.existsByName("ADMIN")) {
            roleService.createRole("ADMIN");
        }
        if (!roleService.existsByName("USER")) {
            roleService.createRole("USER");
        }
        Role adminRole = roleService.findByName("ADMIN");
        Role userRole = roleService.findByName("USER");
        if (!userService.existsByEmail("admin@mail.ru")) {
            userService.add(new User("User1", "Lastname1", "admin@mail.ru",
                    passwordEncoder.encode("admin"),
                    Set.of(adminRole)));
        }

        if (!userService.existsByEmail("user2@mail.ru")) {
            userService.add(new User("User2", "Lastname2", "user2@mail.ru",
                    passwordEncoder.encode("admin"),
                    Set.of(adminRole, userRole)));
        }

        if (!userService.existsByEmail("user3@mail.ru")) {
            userService.add(new User("User3", "Lastname3", "user3@mail.ru",
                    passwordEncoder.encode("admin"),
                    Set.of(userRole)));
        }
    }
}
