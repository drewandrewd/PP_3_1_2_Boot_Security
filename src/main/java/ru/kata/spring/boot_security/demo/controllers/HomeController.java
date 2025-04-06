package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.CategoryService;
import ru.kata.spring.boot_security.demo.services.ProductService;
import ru.kata.spring.boot_security.demo.services.StockMovementService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class HomeController {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final StockMovementService stockMovementService;

    public HomeController(UserService userService,
                          ProductService productService,
                          CategoryService categoryService,
                          StockMovementService stockMovementService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.stockMovementService = stockMovementService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            User currentUser = userService.findByEmail(userDetails.getUsername());
            model.addAttribute("user", currentUser);
        } else {
            model.addAttribute("user", null); // <-- обязательно!
        }

        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("movements", stockMovementService.findAll());
        model.addAttribute("message", "Добро пожаловать в систему учета склада!");

        return "home";
    }

}

