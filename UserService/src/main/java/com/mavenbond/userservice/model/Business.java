package com.mavenbond.userservice.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Business extends Customer {
    @Column
    private String name;

    @Column
    private String contact_email;

    @Column
    private String website_url;
}
