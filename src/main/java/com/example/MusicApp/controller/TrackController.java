package com.example.MusicApp.controller;

import com.example.MusicApp.entity.Track;
import com.example.MusicApp.repository.TrackRepository;
import com.example.MusicApp.service.TrackService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

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


    @GetMapping("/upload")
    public String directUpload() {
        return "Upload";
    }

    @PostMapping("/upload")
    public String addTrack(@RequestPart("file") MultipartFile file, Model model, HttpServletRequest req) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file");
        }
        try {
            String trackUploadDir = "D:/Filenhac";
            String imageUploadDir = "D:/PersonalProject/MusicApp/MusicApp/src/main/resources";
            File uploadDirTrack = new File(trackUploadDir);
            File uploadDirImage = new File(imageUploadDir);
            if (!uploadDirTrack.exists() || !uploadDirImage.exists()) {
              boolean mkdir = uploadDirTrack.mkdirs();
            }
            File saveTrackFile = new File(uploadDirTrack, Objects.requireNonNull(file.getOriginalFilename()));
            File saveImageFile  = new File(uploadDirImage, Objects.requireNonNull(file.getOriginalFilename()));
            String trackTitle = req.getParameter("title");
            int duration = Integer.parseInt(req.getParameter("duration"));
            Date releaseDate = (Date) req.getAttribute("releaseDate");
            String filePath = saveTrackFile.getPath();
            String imagePath = saveImageFile.getPath();
//            Track track = new Track(null, trackTitle, duration, releaseDate, filePath, imagePath);
            file.transferTo(saveTrackFile);
            model.addAttribute("message", "Successfully uploaded ");

            return "Upload";
        }catch (Exception e) {
            model.addAttribute("message", "Failed to upload file");
        }
            return "Upload";
    }

}