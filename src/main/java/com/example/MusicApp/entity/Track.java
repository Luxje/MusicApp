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
    @Column(name = "TrackID")
    private Integer trackId;

    @Column(name = "Title")
    private String title;

    @Column(name = "ArtistName")
    private String artistName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AlbumID")
    private Album album;
    // ------------------------------

    @Column(name = "DurationSeconds")
    private Double durationSeconds;

    @Column(name = "ReleaseDate")
    private Date releaseDate;

    @Column(name = "AudioFileURL")
    private String audioFileURL;

    @Column(name = "ImagePath")
    private String imagePath;


}