package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.StockMovement;
import ru.kata.spring.boot_security.demo.services.ProductService;
import ru.kata.spring.boot_security.demo.services.StockMovementService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.time.LocalDate;

@Controller
@RequestMapping("/movements")
public class StockMovementController {
    private final StockMovementService movementService;
    private final ProductService productService;
    private final UserService userService;

    public StockMovementController(StockMovementService movementService,
                                   ProductService productService,
                                   UserService userService) {
        this.movementService = movementService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public String viewMovements(Model model) {
        model.addAttribute("movements", movementService.findAll());
        return "movements";
    }

    @GetMapping("/add")
    public String addMovementForm(Model model) {
        model.addAttribute("movement", new StockMovement());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("users", userService.getListUsers());
        return "add-movement";
    }

    @PostMapping("/add")
    public String addMovement(@ModelAttribute("movement") StockMovement movement, Model model) {
        movement.setMovementDate(LocalDate.now());
        try {
            movementService.save(movement);
            return "redirect:/movements";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("movement", movement);
            model.addAttribute("products", productService.findAll());
            model.addAttribute("users", userService.getListUsers());
            return "add-movement";
        }
    }


    @PostMapping("/delete")
    public String deleteMovement(@RequestParam("id") Long id) {
        movementService.delete(id);
        return "redirect:/movements";
    }
}