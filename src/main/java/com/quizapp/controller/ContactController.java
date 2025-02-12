package com.quizapp.controller;

import com.quizapp.model.Contact;
import com.quizapp.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("")
    public String showContactPage() {
        return "contact";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String subject,
                              @RequestParam String email,
                              @RequestParam String message,
                              HttpSession session) {
        Contact contactMessage = new Contact(subject, email, message);
        contactService.saveMessage(contactMessage);

        session.setAttribute("contactSuccess", "Your message has been sent successfully!");
        return "redirect:/user/home";
    }

}
