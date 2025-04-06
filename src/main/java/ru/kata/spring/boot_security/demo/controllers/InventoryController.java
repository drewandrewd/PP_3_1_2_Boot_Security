package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Inventory;
import ru.kata.spring.boot_security.demo.services.InventoryService;
import ru.kata.spring.boot_security.demo.services.LocationService;

@Controller
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;
    private final LocationService locationService;

    public InventoryController(InventoryService inventoryService, LocationService locationService) {
        this.inventoryService = inventoryService;
        this.locationService = locationService;
    }

    @GetMapping
    public String viewInventories(Model model) {
        model.addAttribute("inventories", inventoryService.findAll());
        return "inventories";
    }

    @GetMapping("/add")
    public String addInventoryForm(Model model) {
        model.addAttribute("inventory", new Inventory());
        model.addAttribute("locations", locationService.findAll());
        return "add-inventory";
    }

    @PostMapping("/add")
    public String addInventory(@ModelAttribute("inventory") Inventory inventory) {
        inventoryService.save(inventory);
        return "redirect:/inventories";
    }

    @PostMapping("/delete")
    public String deleteInventory(@RequestParam("id") Long id) {
        inventoryService.delete(id);
        return "redirect:/inventories";
    }
}
