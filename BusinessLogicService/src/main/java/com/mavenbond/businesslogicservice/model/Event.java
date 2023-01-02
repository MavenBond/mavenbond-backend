package com.mavenbond.businesslogicservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Event implements Serializable {
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
    private Long business_id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "startDate")
    private Date startDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "endDate")
    private Date endDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Offer> offers;
}
