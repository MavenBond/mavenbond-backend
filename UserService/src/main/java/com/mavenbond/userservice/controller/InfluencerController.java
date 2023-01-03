package com.mavenbond.userservice.controller;

import com.mavenbond.userservice.dto.CustomerInput;
import com.mavenbond.userservice.model.Business;
import com.mavenbond.userservice.model.Influencer;
import com.mavenbond.userservice.repository.BusinessRepository;
import com.mavenbond.userservice.repository.InfluencerRepository;
import com.mavenbond.userservice.service.BusinessService;
import com.mavenbond.userservice.service.InfluencerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/influencer")
@CrossOrigin
public class InfluencerController extends BaseController<Influencer> {

    private final InfluencerService influencerService;

    public InfluencerController(InfluencerRepository repository) {
        super(repository);
        this.influencerService = new InfluencerService(repository) {};
    }

    @PostMapping("/signup")
    public ResponseEntity<Influencer> createCustomer(@RequestBody CustomerInput customerInput){
        Influencer newCustomer = new Influencer();
        newCustomer.setId(customerInput.getId());
        newCustomer.setEmail(customerInput.getEmail());

        // TODO: Add more fields if needed

        influencerService.save(newCustomer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
}
