package com.mavenbond.businesslogicservice.pojo;

import com.mavenbond.businesslogicservice.model.StatusEnum;

public class OfferRequest {
    public Long money;
    public Integer duration;
    public StatusEnum status;
    public String influencerId;
    public String message;
    public Long event_id;
}
