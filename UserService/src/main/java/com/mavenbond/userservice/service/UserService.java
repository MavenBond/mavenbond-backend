package com.mavenbond.userservice.service;

import com.mavenbond.userservice.model.Customer;
import com.mavenbond.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<Customer> findAll() {
        return userRepository.findAll();
    }

    public <S extends Customer> S save(S entity) {
        return userRepository.save(entity);
    }

    public Optional<Customer> findById(String s) {
        return userRepository.findById(s);
    }

    public boolean existsById(String s) {
        return userRepository.existsById(s);
    }

    public void deleteById(String s) {
        userRepository.deleteById(s);
    }
}
