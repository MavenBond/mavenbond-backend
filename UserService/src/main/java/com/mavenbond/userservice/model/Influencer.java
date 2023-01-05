package com.mavenbond.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Influencer extends Customer {
    @Column
    private String facebook_url;

    @Column
    private String instagram_url;

    @Column
    private String youtube_url;

    @Column
    private String tiktok_url;
}
