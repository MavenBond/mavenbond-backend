package com.mavenbond.userservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "influencer")
public class Influencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String facebook_url;

    @Column
    private String instagram_url;

    @Column
    private String youtube_url;

    @Column
    private String tiktok_url;

}
