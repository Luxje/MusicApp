package com.example.MusicApp.service;

import com.example.MusicApp.model.Album;
import com.example.MusicApp.model.Track;
import com.example.MusicApp.repository.AlbumRepository;
import com.example.MusicApp.repository.TrackRepository;
import com.example.MusicApp.util.DurationExtract;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TrackService {

    private AdvancedPlayer player;
    private Thread playerThread;
    private final TrackRepository trackRepository;
    private final AlbumRepository albumRepository;

    private final DurationExtract durationExtract = new DurationExtract();

    public TrackService(TrackRepository trackRepository, AlbumRepository albumRepository) {
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
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
        List<Track> lstTrack = trackRepository.findTracksByTitleContaining(title);
            return lstTrack;
        }


    public boolean uploadTrack(MultipartFile Trackfile, Date releaseDate, String trackTitle, String albumTitle, String ArtistName) {
            try {
                Path trackDir = Paths.get("D:/Filenhac");
                Path imageDir = Paths.get("D:/PersonalProject/MusicApp/Image"); // Better to keep them near each other

                if (!Files.exists(trackDir)) Files.createDirectories(trackDir);
                if (!Files.exists(imageDir)) Files.createDirectories(imageDir);

                String fileName = StringUtils.cleanPath(Objects.requireNonNull(Trackfile.getOriginalFilename()));
                Path trackPath = trackDir.resolve(fileName);
                Path imagePath = imageDir.resolve(fileName);

                // Extract metadata
                Double durationInSecond = durationExtract.getDurationWithTika(Trackfile);
                Album album = albumRepository.findAlbumByTitle(albumTitle);
                // Save the physical file
                Files.copy(Trackfile.getInputStream(), trackPath, StandardCopyOption.REPLACE_EXISTING);

                // Save to DB
                Track track = new Track(null, trackTitle, ArtistName, album, durationInSecond, releaseDate,
                        trackPath.toString(), imagePath.toString());
                trackRepository.save(track);

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }



    public Boolean removeTrack(Integer trackId) {
                trackRepository.deleteById(trackId);
                return true;
            }


////    public void play(String filePath) {
////        stop();
////
////        playerThread = new Thread(() -> {
////            try (FileInputStream fis = new FileInputStream(filePath)) {
////                player = new AdvancedPlayer(fis);
////                System.out.println("Playing: " + filePath);
////
////                // Starts playing the MP3
////                player.play();
////            } catch (Exception e) {
////                System.err.println("Playback error: " + e.getMessage());
////                stop();
////            }
////        });
////        playerThread.start();
////    }
//
//    public void stop() {
//        if (player != null) {
//            player.close();
//            player = null;
//        }
//
//        if (playerThread != null && playerThread.isAlive()) {
//            playerThread.interrupt();
//        }
//
//        System.out.println("Playback stopped.");
//    }



}
