package com.mavenbond.businesslogicservice.controller;

import com.mavenbond.businesslogicservice.model.Offer;
import com.mavenbond.businesslogicservice.repository.OfferRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OfferController {
    private OfferRepository offerRepository;

    @GetMapping("/offers")
    public List<Offer> findAllOffers() {
        return offerRepository.findAll();
    }
}
