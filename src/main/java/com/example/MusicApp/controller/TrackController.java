package com.example.MusicApp.controller;

import com.example.MusicApp.entity.Track;
import com.example.MusicApp.repository.TrackRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController // Use RestController for streaming data/files
@RequestMapping("/api/tracks")
public class TrackController {

    private final TrackRepository trackRepository;

    public TrackController(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
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