package com.example.MusicApp.repository;

import com.example.MusicApp.model.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Integer> {
    public List<Album> findAlbumByArtist_Name(String artistName);

    public List<Album> findAllByTitle(String title);

    public Album findAlbumByTitle(String title);

}
