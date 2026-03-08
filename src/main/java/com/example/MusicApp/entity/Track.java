package com.example.MusicApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrackID") // Added this to match your SQL column name exactly
    private int trackId;

    @Column(name = "Title")
    private String title;

    // --- Database Relationships ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ArtistID")
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AlbumID")
    private Album album;
    // ------------------------------

    @Column(name = "DurationSeconds")
    private int durationSeconds;

    @Column(name = "ReleaseDate")
    private Date releaseDate;

    @Column(name = "AudioFileURL")
    private String audioFileURL;




}