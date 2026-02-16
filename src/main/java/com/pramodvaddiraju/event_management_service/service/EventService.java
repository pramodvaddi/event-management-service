package com.pramodvaddiraju.event_management_service.service;

import com.pramodvaddiraju.event_management_service.dto.EventRequest;
import com.pramodvaddiraju.event_management_service.dto.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    EventResponse createEvent(EventRequest eventRequest);
    Page<EventResponse> getAllEvents(Pageable pageable);
    EventResponse updateEvent(Long id, EventRequest eventRequest);
    EventResponse getEventById(Long id);
    void deleteEventById(Long id);
}
