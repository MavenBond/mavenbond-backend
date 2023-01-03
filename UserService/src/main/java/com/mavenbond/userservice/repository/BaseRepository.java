package com.mavenbond.userservice.repository;

import com.mavenbond.userservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends Customer> extends JpaRepository<T, String> {

    @Query("select u from #{#entityName} as u where u.email = ?1 ")
    Optional<T> findByEmail(String email);
}
