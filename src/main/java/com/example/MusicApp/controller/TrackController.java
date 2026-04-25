package com.example.MusicApp.controller;

import com.example.MusicApp.model.Track;
import com.example.MusicApp.repository.TrackRepository;
import com.example.MusicApp.service.TrackService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController // Use RestController for streaming data/files
@RequestMapping("/api/tracks")
public class TrackController {


    private final TrackRepository trackRepository;
    private final TrackService trackService;

    public TrackController(TrackRepository trackRepository, TrackService trackService) {
        this.trackRepository = trackRepository;
        this.trackService = trackService;
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

//    @PostMapping("/upload")
//    public ResponseEntity<Resource> uploadTrack(@RequestParam("file") MultipartFile file) {
//
//    }


    @GetMapping("/search")
    public ResponseEntity<List<Track>> searchTracks(@RequestParam String title) {
        List<Track> lstTrack = trackService.getTrackByTitle(title);
        return ResponseEntity.ok().body(lstTrack);
    }



}