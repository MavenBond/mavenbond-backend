package com.mavenbond.userservice.controller;


import com.mavenbond.userservice.model.Customer;
import com.mavenbond.userservice.repository.BaseRepository;
import com.mavenbond.userservice.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public abstract class BaseController<T extends Customer> {
    private final BaseService<T> service;

    public BaseController(BaseRepository<T> repository) {
        this.service = new BaseService<>(repository) {};
    }

    @GetMapping("/")
    public ResponseEntity<Page<T>> getAll(@RequestParam(name="pageNo" ,defaultValue = "0") Integer pageNo,
                                          @RequestParam(name="pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name="search", defaultValue = "", required=false) String search,
                                          @RequestParam(name="sortBy", defaultValue = "id", required=false) String sortBy,
                                          @RequestParam(name="isAsc", defaultValue = "false", required=false) Boolean isAsc
    ){
        Pageable pageable = isAsc ? PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending()):
                                    PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        return new ResponseEntity<>(service.findAll(search ,pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getCustomerById(@PathVariable String id){
        Optional<T> customerOptional = service.findById(id);

        return customerOptional.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
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
