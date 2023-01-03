package com.mavenbond.businessservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "offers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
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
    private String influencerId;
    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Event event;

    public Offer(Long money, Integer duration, StatusEnum status, String influencerId, String message, Event event) {
        setMoney(money);
        setDuration(duration);
        setStatus(status);
        setInfluencerId(influencerId);
        setMessage(message);
        setEvent(event);
    }
}
