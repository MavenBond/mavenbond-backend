package com.mavenbond.businessservice.pojo;

import com.mavenbond.businessservice.model.StatusEnum;

public class OfferRequest {
    public Long money;
    public Integer duration;
    public StatusEnum status;
    public String influencerId;
    public String message;
    public Long event_id;
}
