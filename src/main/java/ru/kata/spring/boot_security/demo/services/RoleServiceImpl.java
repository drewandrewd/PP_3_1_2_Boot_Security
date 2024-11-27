package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void createRole(String name) {
        roleRepository.save(new Role(name));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Роль не найдена: " + name));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByName(String name) {
        return roleRepository.findByName(name).isPresent();
    }

    @Transactional(readOnly = true)
    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Роль не найдена: " + id));
    }
}
