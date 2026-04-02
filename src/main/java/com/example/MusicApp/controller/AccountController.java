package com.example.MusicApp.controller;

import com.example.MusicApp.accountCredentials.LoginCredentials;
import com.example.MusicApp.accountCredentials.RegisterCredentials;
import com.example.MusicApp.entity.Users;
import com.example.MusicApp.service.AccountService;
import com.example.MusicApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/register")
    public String directRegister() {
        return "Register";
    }

    @PostMapping("/login")
    public String handleLogin(HttpSession session, Model model, @ModelAttribute("account") LoginCredentials account) {
        String email = account.getEmail();
        String password = account.getPassword();
        if (accountService.validateLogin(email, password)) {
            String displayName = accountService.findAccountByEmail(email).getDisplayName();
            session.setAttribute("email", email);
            session.setAttribute("username", displayName);
            return "redirect:/Music/Home";
        }else {
            model.addAttribute("message", "Invalid email or password");
            return "Login";
        }
    }


    @PostMapping("/register")
    public String handleRegister(Model model,
                                 @RequestParam("signupUsername") String username,
                                 @RequestParam("signupEmail") String email,
                                 @RequestParam("signupPassword") String password,
                                 @RequestPart("imageFile") MultipartFile imageFile,
                                 @RequestParam("displayName") String displayName,
                                 HttpSession session) {
        boolean result = accountService.registerAccount(email, password, username, displayName, null);
        if (result) {
            model.addAttribute("message", "Registration Successful");
            return "redirect:/Account/login";
        }else {
            model.addAttribute("message", "Registration Failed");
            return "Register";
        }
    }
}
