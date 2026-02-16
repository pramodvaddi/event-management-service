package com.pramodvaddiraju.event_management_service.repository;

import com.pramodvaddiraju.event_management_service.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByEventDateBetween(LocalDateTime start, LocalDateTime end);


}
