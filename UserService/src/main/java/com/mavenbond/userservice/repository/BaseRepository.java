package com.mavenbond.userservice.repository;

import com.mavenbond.userservice.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends Customer> extends JpaRepository<T, String> {

    @Query("select u from #{#entityName} as u where u.email = ?1 ")
    Optional<T> findByEmail(String email);

    @Query("SELECT u FROM #{#entityName} as u WHERE u.full_name LIKE %:search% OR u.email LIKE %:search%")
    Page<T> findAll(@Param("search") String search, Pageable pageable);
}
