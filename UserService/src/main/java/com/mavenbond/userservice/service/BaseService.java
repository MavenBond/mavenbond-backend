package com.mavenbond.userservice.service;

import com.mavenbond.userservice.model.Customer;
import com.mavenbond.userservice.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class BaseService<T extends Customer> {
    private final BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public T save(T customer) {
        return repository.save(customer);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Optional<T> findById(String id) {
        return repository.findById(id);
    }

    public Optional<T> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
