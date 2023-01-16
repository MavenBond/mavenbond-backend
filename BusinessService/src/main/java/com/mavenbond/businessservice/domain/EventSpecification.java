package com.mavenbond.businessservice.domain;

import com.mavenbond.businessservice.dto.SpecSearchCriteria;
import com.mavenbond.businessservice.model.Event;

public class EventSpecification extends BaseSpecification<Event> {
    public EventSpecification(SpecSearchCriteria criteria) {
        super(criteria);
    }
}
