package com.example.MusicApp.service;

import com.example.MusicApp.entity.Track;
import com.example.MusicApp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    @Autowired
    TrackRepository trackRepository;

    public List<Track> getTrackByArtistName(String artistName) {
        List<Track> lstTrack = trackRepository.findByArtistName(artistName);
        return lstTrack;
    }

    public void addTrack(Track track) {
        trackRepository.save(track);
    }

    public Boolean removeTrack(Integer trackId) {
        Boolean result = trackRepository.removeTrackByTrackId(trackId);
        if (result) {
            return true;
        }else {
            return false;
        }

    }



}
