package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Location;
import ru.kata.spring.boot_security.demo.services.LocationService;

@Controller
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public String viewLocations(Model model) {
        model.addAttribute("locations", locationService.findAll());
        return "locations";
    }

    @GetMapping("/add")
    public String addLocationForm(Model model) {
        model.addAttribute("location", new Location());
        return "add-location";
    }

    @PostMapping("/add")
    public String addLocation(@ModelAttribute("location") Location location) {
        locationService.save(location);
        return "redirect:/locations";
    }

    @PostMapping("/delete")
    public String deleteLocation(@RequestParam("id") Long id) {
        locationService.delete(id);
        return "redirect:/locations";
    }
}
