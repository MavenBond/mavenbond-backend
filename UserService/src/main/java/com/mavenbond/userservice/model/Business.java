package com.mavenbond.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("business")
public class Business extends Customer {
    @Column
    private String company_name;

    @Column
    private String company_email;

    @Column
    private String website_url;
}
