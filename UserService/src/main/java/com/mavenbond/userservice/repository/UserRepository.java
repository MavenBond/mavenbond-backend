package com.mavenbond.userservice.repository;

import com.mavenbond.userservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Customer, String> {

}
