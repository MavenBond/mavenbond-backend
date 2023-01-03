package com.mavenbond.userservice.service;

import com.mavenbond.userservice.model.Influencer;
import com.mavenbond.userservice.repository.InfluencerRepository;
import org.springframework.stereotype.Service;


@Service
public class InfluencerService extends BaseService<Influencer> {
    public InfluencerService(InfluencerRepository repository) {
        super(repository);
    }
}
