package com.example.MusicApp.service;

import com.example.MusicApp.model.Artist;
import com.example.MusicApp.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    public List<Artist> getArtistByName(String artistName) {
       List<Artist> lstArtists =  artistRepository.findAllByName(artistName);
        return lstArtists;
    }


}
