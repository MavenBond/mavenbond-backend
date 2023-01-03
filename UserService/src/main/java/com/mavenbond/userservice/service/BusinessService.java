package com.mavenbond.userservice.service;

import com.mavenbond.userservice.model.Business;
import com.mavenbond.userservice.repository.BusinessRepository;
import org.springframework.stereotype.Service;


@Service
public class BusinessService extends BaseService<Business> {
    public BusinessService(BusinessRepository repository) {
        super(repository);
    }
}
