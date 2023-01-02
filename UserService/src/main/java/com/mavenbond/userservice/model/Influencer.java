package com.mavenbond.userservice.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Influencer extends Customer {
    @Column
    private String fname;

    @Column
    private String lname;

    @Column
    private String facebook_url;

    @Column
    private String instagram_url;

    @Column
    private String youtube_url;

    @Column
    private String tiktok_url;

}
