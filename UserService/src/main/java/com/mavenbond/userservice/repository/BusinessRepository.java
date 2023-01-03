package com.mavenbond.userservice.repository;

import com.mavenbond.userservice.model.Business;
import jakarta.transaction.Transactional;

@Transactional
public interface BusinessRepository extends BaseRepository<Business> { /* ... */ }
