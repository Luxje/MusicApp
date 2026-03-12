package com.example.MusicApp.controller;

import com.example.MusicApp.entity.Track;
import com.example.MusicApp.service.TrackService;
import com.example.MusicApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mainController")
public class MainController {

    @Autowired
    TrackService trackService;

    @Autowired
    UserService userService;

    @GetMapping("/directMainPage")
    public String directMainPage(Model model) {
        return "MainPage";
    }


    @GetMapping("/mainPage")
    public String mainPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        String username = userService.getTenByEmail(email);
        model.addAttribute("username", username);
        return "MainPage";
    }




}
