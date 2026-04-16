package com.example.MusicApp.controller;

import com.example.MusicApp.entity.Album;
import com.example.MusicApp.entity.Track;
import com.example.MusicApp.repository.TrackRepository;
import com.example.MusicApp.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Music")
public class MainController {

    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final UserService userService;
    private final TrackService trackService;
    private final ArtistService artistService;
    private final TrackRepository trackRepository;
    private final AlbumService albumService;
    private final AccountService accountService;


    public MainController(HttpServletRequest req, HttpServletResponse resp, UserService userService, TrackService trackService, ArtistService artistService, TrackRepository trackRepository, AlbumService albumService, AccountService accountService) {
        this.req = req;
        this.resp = resp;
        this.userService = userService;
        this.trackService = trackService;
        this.artistService = artistService;
        this.trackRepository = trackRepository;
        this.albumService = albumService;
        this.accountService = accountService;
    }

    @GetMapping("/Home")
    public String home(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
//        String displayName = accountService.findAccountByEmail(email).getUsername();
        String artistName = req.getParameter("artistName");
//        model.addAttribute("displayName", displayName);
        model.addAttribute("trackList", trackRepository.findAll());
        req.setAttribute("lstArtist", artistService.getArtistByName(artistName));
        return "MainPage";
    }


    @GetMapping("/Search")
    public String search(Model model, @RequestParam("searchInput") String searchInput) {
        List<Track> lstTrack = trackService.getTrackByTitle(searchInput);
        List<Album> lstAlbum1 = albumService.findByArtistName(searchInput);
        List<Album> lstAlbum2 = albumService.findByAlbumTitle(searchInput);
        if (lstTrack.isEmpty() && lstAlbum1.isEmpty() && lstAlbum2.isEmpty()) { // empty or not.
            model.addAttribute("message", "No tracks or albums found");
        }else {
        // merge lstAlbum1 into lstAlbum2.
            lstAlbum1.addAll(lstAlbum2);
            model.addAttribute("trackList", lstTrack);
            model.addAttribute("albumList", lstAlbum1);
        }
        return "MainPage";
    }


    @GetMapping("/upload")
    public String directUpload() {
        return "Upload";
    }

    @PostMapping("/upload")
    public String addTrack(@RequestPart("file") MultipartFile file, HttpSession session, Model model, @RequestParam("trackTitle") String trackTitle)
    {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file");
            return "upload";
        }

        Date releaseDate = new Date();
        String username = (String) session.getAttribute("username");
        if (trackService.uploadTrack(file, releaseDate, trackTitle, "day la 1 cai album title", username)) {
            model.addAttribute("message", "Successfully uploaded " + file.getOriginalFilename());
            return "upload";
        }
        else {
            model.addAttribute("message", "Failed to upload " + file.getOriginalFilename());
        }
        return "upload";
    }




}
