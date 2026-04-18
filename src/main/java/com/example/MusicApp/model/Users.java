package com.example.MusicApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    @Column(name = "Username")
    private String username;

    @Column(name = "Email")
    private String email;


    @ManyToOne
    @JoinColumn(name = "SubscriptionPlanID")
    private SubscriptionPlan subscriptionPlan;
}
