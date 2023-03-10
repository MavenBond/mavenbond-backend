package com.mavenbond.businessservice.service;

import com.mavenbond.businessservice.model.Event;
import com.mavenbond.businessservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    public Page<Event> findAll(Specification<Event> spec, Pageable pageable) {return repository.findAll(spec, pageable);}

    public void save(Event event) { repository.save(event); }

    public void delete(Long eventId) { repository.deleteById(eventId); }

//    public EventDto findByIdFull(Long id) {
//        Optional<Event> result = repository.findById(id);
//
//        if (result.isPresent()) {
//            Event event = result.get();
//
//            BusinessResponse businessResponse = restTemplate.getForObject(
//                    "http://USERSERVICE/api/v1/business/{businessId}",
//                    BusinessResponse.class,
//                    event.getBusinessId()
//            );
//            return new EventDto(event, businessResponse);
//        }
//
//        return new EventDto(new Event(), new BusinessResponse());
//    }

    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }

    public Page<Event> findAllByBusinessId(String id, Pageable pageable) {
        return repository.findAllByBusinessId(id, pageable);
    }

//    public Page<Event> findAllByBusinessIdAndPlatform(String id, String platform, Pageable pageable) {
//        return repository.findAllByBusinessIdAndPlatform(id, platform, pageable);
//    }
//
//    public Page<Event> findAllByBusinessIdAndType(String id, String type, Pageable pageable) {
//        return repository.findAllByBusinessIdAndType(id, type, pageable);
//    }
}
