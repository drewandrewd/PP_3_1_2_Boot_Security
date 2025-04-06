package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Product;
import ru.kata.spring.boot_security.demo.services.CategoryService;
import ru.kata.spring.boot_security.demo.services.LocationService;
import ru.kata.spring.boot_security.demo.services.ProductService;
import ru.kata.spring.boot_security.demo.services.SupplierService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final LocationService locationService;
    private final SupplierService supplierService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             LocationService locationService,
                             SupplierService supplierService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "add-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") Long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
