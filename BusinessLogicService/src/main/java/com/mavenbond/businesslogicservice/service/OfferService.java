package com.mavenbond.businesslogicservice.service;

import com.mavenbond.businesslogicservice.model.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<Offer> findAllOffers();
    void saveOffer(Offer offer);
    void deleteOffer(Long offerId);
    Optional<Offer> findOfferById(Long offerId);
}
