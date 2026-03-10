package com.example.MusicApp.controller;

import com.example.MusicApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainController")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/mainPage")
    public String mainPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        String username = userService.getTenByEmail(email);
        model.addAttribute("username", username);
        return "MainPage";
    }




}
