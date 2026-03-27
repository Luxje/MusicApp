package com.example.MusicApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Username")
    private String username;

    @Column(name = "Email")
    private String email;

    @Column(name = "PasswordHash")
    private String password;

    @Column(name = "DisplayName")
    private String displayName;

    @Column(name = "ProfilePictureUrl")
    private String profilePictureUrl;

    @Column(name = "Bio")
    private String bio;

    @Column(name = "AccountRole")
    private String accountRole;

    @Column(name = "IsVerified")
    private Boolean isVerified;

    @Column(name = "TotalPlays")
    private Integer totalPlays;


}
