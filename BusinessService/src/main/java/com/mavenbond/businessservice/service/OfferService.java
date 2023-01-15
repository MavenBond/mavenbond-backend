package com.mavenbond.businessservice.service;

import com.mavenbond.businessservice.model.Offer;
import com.mavenbond.businessservice.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferService {
    @Autowired
    private OfferRepository repository;

    public Page<Offer> findAll(Specification<Offer> spec, Pageable pageable) { return repository.findAll(spec, pageable); }

    public void save(Offer offer) { repository.save(offer); }

    public void delete(Long offerId) { repository.deleteById(offerId); }

    public Optional<Offer> findById(Long id) { return repository.findById(id); }
}
