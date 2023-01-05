package com.mavenbond.userservice.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="customer")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = Business.class, name = "Business"),
        @JsonSubTypes.Type(value = Influencer.class, name = "Influencer"),
})
public abstract class Customer {
    @Id
    private String id;

    @Column(nullable = false)
    private String email;

    @Column
    private String full_name;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String phone;

    @Column
    private String image_url;

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="favorite",
            joinColumns={@JoinColumn(name="customer_id")},
            inverseJoinColumns={@JoinColumn(name="favorite_id")})
    private Set<Customer> favorite = new HashSet<Customer>();

    @ManyToMany(mappedBy="favorite")
    private Set<Customer> favorite_by = new HashSet<Customer>();
}
