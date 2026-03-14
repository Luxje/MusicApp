package com.example.MusicApp.repository;

import com.example.MusicApp.entity.Track;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, Integer> {
    public List<Track> findByArtistName(String artistName);

    public Track findTrackByTitle(String title);
}
