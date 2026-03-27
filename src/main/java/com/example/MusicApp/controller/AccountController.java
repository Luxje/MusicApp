package com.example.MusicApp.controller;

import com.example.MusicApp.entity.Users;
import com.example.MusicApp.service.AccountService;
import com.example.MusicApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Account")
public class AccountController {

    private final UserService userService;
    private final AccountService accountService;

    public AccountController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String directLogin() {
        return "Login";
    }

    @PostMapping("/login")
    public String handleLogin(HttpSession session, Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        if (userService.validateLogin(email, password)) {
            String displayName = accountService.findAccountByEmail(email).getDisplayName();
            session.setAttribute("email", email);
            session.setAttribute("username", displayName);
            return "redirect:/Music/Home";
        }else {
            model.addAttribute("message", "Invalid email or password");
            return "Login";
        }
    }


    @GetMapping("/register")
    public String directRegister() {
        return "Register";
    }


    @PostMapping("/register")
    public String handleRegister(HttpServletRequest req, HttpSession session, Model model) {
        String email = req.getParameter("signupEmail");
        String password = req.getParameter("signupPassword");
        String username = req.getParameter("signupUsername");
        Boolean result = userService.validateRegister(username, email, password, 1);

        if (result) {
            model.addAttribute("message", "Registration Successful");
            return "redirect:/Account/login";
        }else {
            model.addAttribute("message", "Registration Failed");
            return "Register";
        }

    }
}
