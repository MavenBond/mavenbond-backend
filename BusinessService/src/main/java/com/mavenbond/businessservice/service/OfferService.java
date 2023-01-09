package com.mavenbond.businessservice.service;

import com.mavenbond.businessservice.model.Offer;
import com.mavenbond.businessservice.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    @Autowired
    private OfferRepository repository;

    public List<Offer> findAll() { return repository.findAll(); }

    public void save(Offer offer) { repository.save(offer); }

    public void delete(Long offerId) { repository.deleteById(offerId); }

    public Optional<Offer> findById(Long id) { return repository.findById(id); }
}
