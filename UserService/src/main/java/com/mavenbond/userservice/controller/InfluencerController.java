package com.mavenbond.userservice.controller;

import com.mavenbond.userservice.dto.CustomerInput;
import com.mavenbond.userservice.engine.Producer;
import com.mavenbond.userservice.model.Business;
import com.mavenbond.userservice.model.Influencer;
import com.mavenbond.userservice.repository.InfluencerRepository;
import com.mavenbond.userservice.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/influencer")
@CrossOrigin(origins = "*")
public class InfluencerController extends BaseController<Influencer> {
    private final InfluencerService influencerService;
    @Autowired
    private Producer kafkaProducer;

    public InfluencerController(InfluencerRepository repository) {
        super(repository);
        this.influencerService = new InfluencerService(repository) {};
    }

    @PostMapping("/signup")
    public ResponseEntity<Influencer> createCustomer(@RequestBody Influencer influencer){

        // Check if user already existed
        Optional<Influencer> customerOptional = influencerService.findById(influencer.getId());

        return customerOptional.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> {
                    kafkaProducer.sendSaveRequest(influencer, "SAVE_INFLUENCER");
//                    influencerService.save(influencer);
                    return new ResponseEntity<>(influencer, HttpStatus.CREATED);
                });


    }
}
