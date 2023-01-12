package com.mavenbond.userservice.controller;


import com.mavenbond.userservice.model.Customer;
import com.mavenbond.userservice.repository.BaseRepository;
import com.mavenbond.userservice.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public abstract class BaseController<T extends Customer> {
    private final BaseService<T> service;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public BaseController(BaseRepository<T> repository) {
        this.service = new BaseService<>(repository) {};
    }

    @GetMapping("/")
    public ResponseEntity<Page<T>> getAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize
//            @RequestParam(defaultValue = "id") String sortBy
    ){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @Cacheable(value = "userCache", key = "#id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public T getCustomerById(@PathVariable String id){
        Optional<T> customerOptional = service.findById(id);
        LOG.info("Getting user with ID {}.", id);

        return customerOptional
                .orElseGet(() -> (null));
    }

    @GetMapping("/email/")
    public ResponseEntity<T> getCustomerByEmail(@RequestParam String email){
        Optional<T> customerOptional =  service.findByEmail(email);

        return customerOptional.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> updateUser(@PathVariable String id, @RequestBody T customer){
        Optional<T> customerOptional =  service.findById(id);

        return customerOptional.map(customerTmp -> {
            customer.setId(customerTmp.getId());
            return new ResponseEntity<>(service.save(customer), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<T> deleteUser(@PathVariable String id){
        Optional<T> customerOptional =  service.findById(id);

        return customerOptional.map(customer -> {
            service.delete(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }
}
