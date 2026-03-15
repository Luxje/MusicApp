package com.example.MusicApp.controller;

import com.example.MusicApp.entity.Track;
import com.example.MusicApp.repository.TrackRepository;
import com.example.MusicApp.service.ArtistService;
import com.example.MusicApp.service.TrackService;
import com.example.MusicApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/mainController")
public class MainController {

    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final UserService userService;
    private final TrackService trackService;
    private final ArtistService artistService;
    private final TrackRepository trackRepository;


    public MainController(HttpServletRequest req, HttpServletResponse resp, UserService userService, TrackService trackService, ArtistService artistService, TrackRepository trackRepository) {
        this.req = req;
        this.resp = resp;
        this.userService = userService;
        this.trackService = trackService;
        this.artistService = artistService;
        this.trackRepository = trackRepository;

    }

    @GetMapping("/directMainPage")
    public String directMainPage(Model model) {
        return "MainPage";
    }


    @GetMapping("/mainPage")
    public String mainPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        String username = userService.getTenByEmail(email);
        String artistName = req.getParameter("artistName");
        model.addAttribute("username", username);

        model.addAttribute("trackList", trackRepository.findAll());




        req.setAttribute("lstArtist", artistService.getArtistByName(artistName));
        return "MainPage";
    }










}
