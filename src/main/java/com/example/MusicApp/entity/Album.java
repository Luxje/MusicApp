package com.example.MusicApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int albumId;

    @Column(name = "Title")
    private String title;

    @Column(name = "Genre")
    private String genre;

    @Column(name = "ReleaseDate")
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "ArtistID", referencedColumnName = "albumId")
    private Artist artist;
}
