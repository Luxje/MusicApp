package com.example.MusicApp.repository;


import com.example.MusicApp.entity.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    public List<Artist> findAllByName(String name);

    public Artist findByName(String name);
}
