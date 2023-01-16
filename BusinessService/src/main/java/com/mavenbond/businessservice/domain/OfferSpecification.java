package com.mavenbond.businessservice.domain;

import com.mavenbond.businessservice.dto.SpecSearchCriteria;
import com.mavenbond.businessservice.model.Offer;
import jakarta.transaction.Transactional;


@Transactional
public class OfferSpecification extends BaseSpecification<Offer> {
    public OfferSpecification(SpecSearchCriteria criteria) {
        super(criteria);
    }
}
