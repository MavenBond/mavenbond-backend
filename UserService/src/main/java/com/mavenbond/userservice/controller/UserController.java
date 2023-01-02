package com.mavenbond.userservice.controller;

import com.mavenbond.userservice.model.Business;
import com.mavenbond.userservice.model.Customer;
import com.mavenbond.userservice.dto.CustomerInput;
import com.mavenbond.userservice.model.Influencer;
import com.mavenbond.userservice.service.UserService;
import com.mavenbond.userservice.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import static com.mavenbond.userservice.utils.Utilities.encryptPassword;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    // Business Route
    @PostMapping("/business/signup")
    public ResponseEntity<Customer> createBusiness(@RequestBody CustomerInput customerInput){
        String encodedPassword = encryptPassword(customerInput.getPassword());

        Business newBusiness = new Business();
        newBusiness.setId(customerInput.getId());
        newBusiness.setEmail(customerInput.getEmail());
        newBusiness.setPassword(encodedPassword);

        // TODO: Add more fields if needed

        userService.create(newBusiness);
        return new ResponseEntity<>(newBusiness, HttpStatus.CREATED);
    }

    // Influencer Route
    @PostMapping("/influencer/signup")
    public ResponseEntity<Customer> createInfluencer(@RequestBody CustomerInput customerInput){
        String encodedPassword = encryptPassword(customerInput.getPassword());

        Influencer newInfluencer = new Influencer();
        newInfluencer.setId(customerInput.getId());
        newInfluencer.setEmail(customerInput.getEmail());
        newInfluencer.setPassword(encodedPassword);

        // TODO: Add more fields if needed

        userService.create(newInfluencer);
        return new ResponseEntity<>(newInfluencer, HttpStatus.CREATED);
    }

    // Common route
    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize
//            @RequestParam(defaultValue = "id") String sortBy
    ){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getUser(@PathVariable String id){
        Optional<Customer> customerOptional =  userService.findById(id);

        return customerOptional.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateUser(@PathVariable String id, @RequestBody Customer customer){
        Optional<Customer> customerOptional =  userService.findById(id);

        return customerOptional.map(customerTmp -> {
            customer.setId(customerTmp.getId());
            return new ResponseEntity<>(userService.save(customer), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteUser(@PathVariable String id){
        Optional<Customer> customerOptional =  userService.findById(id);

        return customerOptional.map(customer -> {
            userService.delete(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
