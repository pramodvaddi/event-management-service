package com.pramodvaddiraju.event_management_service.service;

import com.pramodvaddiraju.event_management_service.dto.EventRequest;
import com.pramodvaddiraju.event_management_service.dto.EventResponse;
import com.pramodvaddiraju.event_management_service.entity.Event;
import com.pramodvaddiraju.event_management_service.exception.ResourceNotFoundExcpetion;
import com.pramodvaddiraju.event_management_service.notifications.EmailService;
import com.pramodvaddiraju.event_management_service.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventServiceImpl implements EventService{

    private ModelMapper modelMapper;
    private EventRepository eventRepository;
    private EmailService emailService;
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    public EventServiceImpl(ModelMapper modelMapper, EventRepository eventRepository, EmailService emailService){
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
    }

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {
        Event event = modelMapper.map(eventRequest, Event.class);
        log.info("Received create request with id: {}", event.getId());
        event.setCreatedAt(LocalDateTime.now());
        Event created = eventRepository.save(event);
        emailService.sendEventCreatedMail(created.getOrganiserEmail(), created.getTitle());
        log.info("Event Created with id: {}", event.getId());

        return modelMapper.map(created, EventResponse.class);
    }

    @Override
    public Page<EventResponse> getAllEvents(Pageable pageable) {

        return eventRepository.findAll(pageable).map(event -> modelMapper.map(event, EventResponse.class));
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest eventRequest) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExcpetion("Event not found with id: " + id)
        );

        existingEvent.setTitle(eventRequest.getTitle());
        existingEvent.setDescription(eventRequest.getDescription());
        existingEvent.setLocation(eventRequest.getLocation());
        existingEvent.setEventDate(eventRequest.getEventDate());
        existingEvent.setOrganiserEmail(eventRequest.getOrganiserEmail());

        Event updateEvent = eventRepository.save(existingEvent);
        log.info("Event update successful with id: {}", updateEvent.getId());
        return modelMapper.map(updateEvent, EventResponse.class);
    }

    @Override
    public EventResponse getEventById(Long id) {
        Event getById = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExcpetion("Event not found with id: " + id)
        );
        return modelMapper.map(getById, EventResponse.class);
    }

    @Override
    public void deleteEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExcpetion("Event not found with id: " + id)
        );

        eventRepository.delete(event);

        log.info("Event deleted successfully with id: {}", id);
    }
}
