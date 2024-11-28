package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return userService.findByEmail(userDetails.getUsername());
    }

    @GetMapping("/")
    public String printUsers(ModelMap model) {
        model.addAttribute("user", getCurrentUser());
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/addUser")
    public String addUserForm(ModelMap model) {
        model.addAttribute("currUser", getCurrentUser());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.getAllRoles());
            return "add-user";
        }
        Set<Role> selectedRoles = user.getRoles().stream()
                .map(role -> roleService.findByName(role.getName()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        user.setRoles(selectedRoles);
        userService.add(user);
        return "redirect:/admin/";
    }

@PostMapping("/editUser")
@ResponseBody
public ResponseEntity<User> editUser(@ModelAttribute User user) {
    User currUser = userService.edit(user);
    return ResponseEntity.ok(currUser);
}

@PostMapping("/deleteUser")
@ResponseBody
public ResponseEntity<Void> deleteUser(@ModelAttribute User user) {
    userService.delete(user.getId());
    return ResponseEntity.ok().build();
}

}
