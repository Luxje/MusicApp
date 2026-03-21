package com.example.MusicApp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Feed")
public class FeedController {
    @GetMapping("/Feed")
    public String feed(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        model.addAttribute("email", email);
        return "Feed";
    }


}
