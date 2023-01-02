package com.mavenbond.businesslogicservice.model;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "offers")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "money")
    private Long money;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "status")
    private StatusEnum status;
    @Column(name = "influencer_id")
    private Long influencer_id;
    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Event event;
}
