package com.example.MusicApp.service;

import com.example.MusicApp.entity.Album;
import com.example.MusicApp.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findByArtistName(String artistName) {
        List<Album> lstAlbum = albumRepository.findAlbumByArtist_Name(artistName) ;
        if (!lstAlbum.isEmpty()) {
            return lstAlbum;
        }else {
            return null;
        }
    }

    public List<Album> findByAlbumTitle(String albumTitle) {
        List<Album> lstAlbum = albumRepository.findAlbumByTitle(albumTitle);
        if (!lstAlbum.isEmpty()) {
            return lstAlbum;
        }else {
            return null;
        }
    }
}
