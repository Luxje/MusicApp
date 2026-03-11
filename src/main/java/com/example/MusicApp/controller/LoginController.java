package com.example.MusicApp.controller;

import com.example.MusicApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginController")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/directLogin")
    public String directLogin() {
        return "Login";
    }

    @PostMapping("/handleLogin")
    public String handleLogin(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (userService.validateLogin(email, password)) {
            session.setAttribute("email", email);
            session.setAttribute("message", "Login Successful");
            return "redirect:/mainController/mainPage";
        }else {
            session.setAttribute("message", "Login Failed");
            return "Login";
        }
    }
}
