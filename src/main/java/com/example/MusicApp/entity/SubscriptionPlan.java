package com.example.MusicApp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SubscriptionPlan")
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriptionPlanID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Description")
    private String description;
}
