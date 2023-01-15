package com.mavenbond.businessservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "subject")
    private String subject;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @Column(name = "platform")
    @Enumerated(EnumType.STRING)
    private PlatformEnum platform;

    @Column(name = "description")
    private String description;

    @Column(name = "moneyMin")
    private Long moneyMin;

    @Column(name = "moneyMax")
    private Long moneyMax;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @Column(name = "business_id")
    private String businessId;

    @Column(name = "business_email")
    private String businessEmail;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "startDate")
    private Long startDate;

    @Column(name = "endDate")
    private Long endDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Offer> offers;
}
