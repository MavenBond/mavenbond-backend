package com.mavenbond.userservice.service;

import com.mavenbond.userservice.model.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    <S extends Customer> S save(S entity);
    Customer create(Customer customer);
    Customer update(String id, Customer customer);
    void delete(String id);
    Optional<Customer> findById(String id);
    List<Customer> findAll(Pageable pageable);
}
