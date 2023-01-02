package com.mavenbond.businesslogicservice.service;

import com.mavenbond.businesslogicservice.model.Offer;
import com.mavenbond.businesslogicservice.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService{
    @Autowired
    private OfferRepository repository;

    @Override
    public List<Offer> findAllOffers() {return repository.findAll();}

    @Override
    public void saveOffer(Offer offer) { repository.save(offer); }

    @Override
    public void deleteOffer(Long offerId) { repository.deleteById(offerId); }

    @Override
    public Optional<Offer> findOfferById(Long offerId) { return repository.findById(offerId); }
}
