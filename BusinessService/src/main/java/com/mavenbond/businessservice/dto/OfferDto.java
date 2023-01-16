package com.mavenbond.businessservice.dto;

import com.mavenbond.businessservice.model.Offer;
import com.mavenbond.businessservice.model.PlatformEnum;
import com.mavenbond.businessservice.model.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OfferDto {
    private Long id;
    private String influencerId;
    private String businessId;
    private String businessEmail;
    private String businessName;
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String message;
    private Long acceptPrice;
    private String unit = "USD";
    private PlatformEnum platform;
    private StatusEnum status;
    private Long startDate;
    private Long endDate;
    private Integer duration;

    public OfferDto (Offer offer) {
        this.id = offer.getId();
        this.influencerId = offer.getInfluencerId();
        this.businessId = offer.getEvent().getBusinessId();
        this.businessEmail = offer.getEvent().getBusinessEmail();
        this.businessName = offer.getEvent().getBusinessName();
        this.eventId = offer.getEvent().getId();
        this.eventName = offer.getEvent().getTitle();
        this.eventDescription = offer.getEvent().getDescription();
        this.message = offer.getMessage();
        this.acceptPrice = offer.getMoney();
        this.platform = offer.getEvent().getPlatform();
        this.status = offer.getStatus();
        this.startDate = offer.getEvent().getStartDate();
        this.endDate = offer.getEvent().getEndDate();
        this.duration = offer.getDuration();
    }
}
