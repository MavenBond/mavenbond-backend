package com.mavenbond.businessservice.repository;

import com.mavenbond.businessservice.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    @Query("SELECT e FROM Event e WHERE e.title LIKE %:key%")
    Page<Event> findAllByKeyLike(@Param("key") String key, Pageable pageable);

    Page<Event> findAllByBusinessId(String id, Pageable pageable);

//    Page<Event> findAllByBusinessIdAndPlatform(String id, String platform, Pageable pageable);
//    Page<Event> findAllByBusinessIdAndType(String id, String type, Pageable pageable);
}
