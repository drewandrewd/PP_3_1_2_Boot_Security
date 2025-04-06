package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Supplier;
import ru.kata.spring.boot_security.demo.services.SupplierService;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public String viewSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        return "suppliers";
    }

    @GetMapping("/add")
    public String addSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "add-supplier";
    }

    @PostMapping("/add")
    public String addSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @PostMapping("/delete")
    public String deleteSupplier(@RequestParam("id") Long id) {
        supplierService.delete(id);
        return "redirect:/suppliers";
    }
}
