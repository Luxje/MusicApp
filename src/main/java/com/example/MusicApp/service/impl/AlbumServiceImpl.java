package com.example.MusicApp.service.impl;

import com.example.MusicApp.repository.AlbumRepository;
import com.example.MusicApp.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;




//    public List<Album> findByArtistName(String artistName) {
//        List<Album> lstAlbum = albumRepository.findAlbumByArtist_Name(artistName) ;
//        if (!lstAlbum.isEmpty()) {
//            return lstAlbum;
//        }else {
//            return null;
//        }
//    }
//
//    public List<Album> findByAlbumTitle(String albumTitle) {
//        List<Album> lstAlbum = albumRepository.findAllByTitle(albumTitle);
//        if (!lstAlbum.isEmpty()) {
//            return lstAlbum;
//        }else {
//            return null;
//        }
//    }
}
