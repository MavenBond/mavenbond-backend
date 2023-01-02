package com.mavenbond.userservice.service;

import com.mavenbond.userservice.model.Customer;
import com.mavenbond.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mavenbond.userservice.utils.Utilities.encryptPassword;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public <S extends Customer> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public Customer create(Customer customer) {
        if (customer.getEmail() == null  || customer.getEmail().isEmpty()) {
            return null;
        }

        if (customer.getId() == null  || customer.getId().isEmpty()) {
            return null;
        }

        if (customer.getPassword() == null  || customer.getPassword().isEmpty()) {
            return null;
        }

        return userRepository.save(customer);
    }

    @Override
    public Customer update(String id, Customer input) {
        Customer customer = userRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }

        if (input.getEmail() != null && !input.getEmail().equals(customer.getEmail())) {
            customer.setEmail(input.getEmail());
        }

        if (input.getPassword() != null && !encryptPassword(input.getPassword()).equals(customer.getPassword())) {
            customer.setPassword(encryptPassword(input.getPassword()));
        }

        return userRepository.save(customer);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return userRepository.findById(id);
    }

    public List<Customer> findAll(Pageable pageable) {
        Page<Customer> pagedResult = userRepository.findAll(pageable);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Customer>();
        }
    }
}
