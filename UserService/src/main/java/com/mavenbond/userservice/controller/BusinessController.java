package com.mavenbond.userservice.controller;

import com.mavenbond.userservice.dto.CustomerInput;
import com.mavenbond.userservice.model.Business;
import com.mavenbond.userservice.model.Customer;
import com.mavenbond.userservice.repository.BaseRepository;
import com.mavenbond.userservice.repository.BusinessRepository;
import com.mavenbond.userservice.service.BaseService;
import com.mavenbond.userservice.service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/business")
@CrossOrigin
public class BusinessController extends BaseController<Business> {
    private final BusinessService businessService;

    public BusinessController(BusinessRepository repository) {
        super(repository);
        this.businessService = new BusinessService(repository) {};
    }

    @PostMapping("/signup")
    public ResponseEntity<Business> createCustomer(@RequestBody CustomerInput customerInput){
        Business newCustomer = new Business();
        newCustomer.setId(customerInput.getId());
        newCustomer.setEmail(customerInput.getEmail());

        // TODO: Add more fields if needed

        businessService.save(newCustomer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
}
