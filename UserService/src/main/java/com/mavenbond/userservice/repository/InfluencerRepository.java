package com.mavenbond.userservice.repository;

import com.mavenbond.userservice.model.Influencer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Transactional
public interface InfluencerRepository extends BaseRepository<Influencer> { /* ... */ }
