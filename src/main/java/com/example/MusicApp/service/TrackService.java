package com.example.MusicApp.service;

import com.example.MusicApp.entity.Track;
import com.example.MusicApp.repository.TrackRepository;
import jakarta.servlet.http.HttpServletRequest;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TrackService {

    private AdvancedPlayer player;
    private Thread playerThread;
    private final TrackRepository trackRepository;


    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<Track> getTrackByArtistName(String artistName) {

        List<Track> lstTrack = trackRepository.findByArtistName(artistName);
        if (lstTrack.isEmpty()) {
            System.out.println("No track found with name " + artistName);
            return null;
        }else {
            return lstTrack;
        }
    }


    public List<Track> getTrackByTitle(String title) {
        List<Track> lstTrack = trackRepository.findTracksByTitle(title);
            return lstTrack;
        }


    public void uploadTrack(MultipartFile file, Model model, HttpServletRequest req) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
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

        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Upload failed");

        }
    }

    public void deleteTrack(Integer trackId) {
        trackRepository.deleteById(trackId);
    }

//    public Boolean removeTrack(Integer trackId) {
//        Boolean result = trackRepository.removeTrackByTrackId(trackId);
//        if (result) {
//            return true;
//        }else {
//            return false;
//        }
//    }


    public void play(String filePath) {
        stop();

        playerThread = new Thread(() -> {
            try (FileInputStream fis = new FileInputStream(filePath)) {
                player = new AdvancedPlayer(fis);
                System.out.println("Playing: " + filePath);

                // Starts playing the MP3
                player.play();
            } catch (Exception e) {
                System.err.println("Playback error: " + e.getMessage());
                stop();
            }
        });
        playerThread.start();
    }

    public void stop() {
        if (player != null) {
            player.close();
            player = null;
        }

        if (playerThread != null && playerThread.isAlive()) {
            playerThread.interrupt();
        }

        System.out.println("Playback stopped.");
    }



}
