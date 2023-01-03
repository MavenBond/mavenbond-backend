package com.mavenbond.businessservice.service;

import com.mavenbond.businessservice.model.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<Offer> findAll();
    void save(Offer offer);
    void delete(Long offerId);
    Optional<Offer> findById(Long id);
}
