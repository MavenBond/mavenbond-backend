package com.mavenbond.userservice.controller;

import com.mavenbond.userservice.dto.CustomerInput;
import com.mavenbond.userservice.model.Business;
import com.mavenbond.userservice.repository.BusinessRepository;
import com.mavenbond.userservice.service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/business")
@CrossOrigin(origins = "*")
public class BusinessController extends BaseController<Business> {
    private final BusinessService businessService;

    public BusinessController(BusinessRepository repository) {
        super(repository);
        this.businessService = new BusinessService(repository) {};
    }

    @PostMapping("/signup")
    public ResponseEntity<Business> createCustomer(@RequestBody Business business){

        // Check if user already existed
        Optional<Business> customerOptional = businessService.findById(business.getId());

        return customerOptional.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> {
                    businessService.save(business);
                    return new ResponseEntity<>(business, HttpStatus.CREATED);
                });
    }
}
