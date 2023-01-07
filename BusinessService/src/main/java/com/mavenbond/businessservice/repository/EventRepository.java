package com.mavenbond.businessservice.repository;

import com.mavenbond.businessservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from #{#entityName} as e where e.businessId = ?1 ")
    Optional<List<Event>> findByUserId(String id);
}
