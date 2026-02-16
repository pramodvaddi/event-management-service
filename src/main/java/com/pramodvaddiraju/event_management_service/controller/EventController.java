package com.pramodvaddiraju.event_management_service.controller;

import com.pramodvaddiraju.event_management_service.dto.EventRequest;
import com.pramodvaddiraju.event_management_service.dto.EventResponse;
import com.pramodvaddiraju.event_management_service.service.EventService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private static final Logger log = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;


    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest eventRequest){
        log.info("Created Event wit id:");
        return ResponseEntity.status(201).body(eventService.createEvent(eventRequest));
    }

    @GetMapping
    ResponseEntity<Page<EventResponse>> getAllEvents(Pageable pageable){
        return ResponseEntity.ok().body(eventService.getAllEvents(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<EventResponse> getEventById(@PathVariable Long id){
        return ResponseEntity.ok().body(eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<EventResponse> updateEvent(@PathVariable Long id, @RequestBody EventRequest eventRequest){
        EventResponse updatedEvent = eventService.updateEvent(id, eventRequest);
        return ResponseEntity.ok().body(updatedEvent);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }

}
