package com.example.MusicApp.controller;

import com.example.MusicApp.entity.Album;
import com.example.MusicApp.entity.Artist;
import com.example.MusicApp.entity.Track;
import com.example.MusicApp.repository.AlbumRepository;
import com.example.MusicApp.repository.ArtistRepository;
import com.example.MusicApp.repository.TrackRepository;
import com.example.MusicApp.service.AlbumService;
import com.example.MusicApp.service.ArtistService;
import com.example.MusicApp.service.TrackService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RestController // Use RestController for streaming data/files
@RequestMapping("/api/tracks")
public class TrackController {


    private final TrackRepository trackRepository;
    private final TrackService trackService;
    private final ArtistService artistService;
    private final ArtistRepository artistRepository;
    private final AlbumService albumService;
    private final AlbumRepository albumRepository;

    public TrackController(TrackRepository trackRepository, TrackService trackService, ArtistService artistService, ArtistRepository artistRepository, AlbumService albumService, AlbumRepository albumRepository) {
        this.trackRepository = trackRepository;
        this.trackService = trackService;
        this.artistService = artistService;
        this.artistRepository = artistRepository;
        this.albumService = albumService;
        this.albumRepository = albumRepository;
    }

    @GetMapping("/stream/{id}")
    public ResponseEntity<Resource> streamTrack(@PathVariable Integer id) {
        // 1. Find the track in the DB
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Track not found with id: " + id));

        // 2. Get the file path from your entity
        Path path = Paths.get(track.getAudioFileURL());
        Resource resource = new FileSystemResource(path);

        // 3. Return the stream
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + track.getTitle() + ".mp3\"")
                .body(resource);
    }



}