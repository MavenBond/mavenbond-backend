package com.mavenbond.userservice.controller;

import com.mavenbond.userservice.model.Business;
import com.mavenbond.userservice.model.Customer;
import com.mavenbond.userservice.model.CustomerInput;
import com.mavenbond.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/business/signup")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerInput customerInput){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(customerInput.getPassword());

        Business newBusiness = new Business();
        newBusiness.setId(customerInput.getId());
        newBusiness.setEmail(customerInput.getEmail());
        newBusiness.setPassword(encodedPassword);
        
        userService.save(newBusiness);
        return new ResponseEntity<>(newBusiness, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id){
//        Optional<User> userUpdate = userService.findById(id);

        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id){
//        Optional<User> userToDelete = userService.findById(id);
        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
