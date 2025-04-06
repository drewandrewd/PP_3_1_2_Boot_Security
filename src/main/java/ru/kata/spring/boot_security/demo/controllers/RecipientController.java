package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Recipient;
import ru.kata.spring.boot_security.demo.services.RecipientService;

@Controller
@RequestMapping("/recipients")
public class RecipientController {
    private final RecipientService recipientService;

    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @GetMapping
    public String viewRecipients(Model model) {
        model.addAttribute("recipients", recipientService.findAll());
        return "recipients";
    }

    @GetMapping("/add")
    public String addRecipientForm(Model model) {
        model.addAttribute("recipient", new Recipient());
        return "add-recipient";
    }

    @PostMapping("/add")
    public String addRecipient(@ModelAttribute("recipient") Recipient recipient) {
        recipientService.save(recipient);
        return "redirect:/recipients";
    }

    @PostMapping("/delete")
    public String deleteRecipient(@RequestParam("id") Long id) {
        recipientService.delete(id);
        return "redirect:/recipients";
    }
}
